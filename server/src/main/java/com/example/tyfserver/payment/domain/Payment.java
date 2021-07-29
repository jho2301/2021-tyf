package com.example.tyfserver.payment.domain;

import com.example.tyfserver.common.domain.BaseTimeEntity;
import com.example.tyfserver.payment.exception.IllegalPaymentInfoException;
import com.example.tyfserver.payment.exception.PaymentRequestException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static com.example.tyfserver.payment.exception.PaymentRequestException.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long amount;

    private String email;

    @Column(nullable = false)
    private String pageName;

    @Enumerated(value = EnumType.STRING)
    private PaymentStatus status = PaymentStatus.PENDING;

    private String impUid;

    public Payment(Long id, Long amount, String email, String pageName) {
        this.id = id;
        this.amount = amount;
        this.email = email;
        this.pageName = pageName;
    }

    public Payment(Long amount, String email, String pageName) {
        this(null, amount, email, pageName);
    }

    public void complete(PaymentInfo paymentInfo) {
        validatePaymentComplete(paymentInfo);
        this.impUid = paymentInfo.getImpUid();
        this.status = PaymentStatus.PAID;
    }

    public void updateStatus(PaymentStatus paymentStatus) {
        this.status = paymentStatus;
    }

    private void validatePaymentComplete(PaymentInfo paymentInfo) {
        if (!PaymentStatus.isPaid(paymentInfo.getStatus())) {
            updateStatus(paymentInfo.getStatus());
            throw IllegalPaymentInfoException.from(IllegalPaymentInfoException.ERROR_CODE_NOT_PAID, paymentInfo.getModule());
        }

        validatePaymentInfo(paymentInfo);
    }

    private void validatePaymentInfo(PaymentInfo paymentInfo) {
        if (!id.equals(paymentInfo.getMerchantId())) {
            updateStatus(PaymentStatus.INVALID);
            throw IllegalPaymentInfoException.from(ERROR_CODE_INVALID_MERCHANT_ID, paymentInfo.getModule());
        }

        if (!amount.equals(paymentInfo.getAmount())) {
            updateStatus(PaymentStatus.INVALID);
            throw IllegalPaymentInfoException.from(ERROR_CODE_INVALID_AMOUNT, paymentInfo.getModule());
        }

        if (!pageName.equals(paymentInfo.getPageName())) {
            updateStatus(PaymentStatus.INVALID);
            throw IllegalPaymentInfoException.from(ERROR_INVALID_CREATOR, paymentInfo.getModule());
        }
    }
}