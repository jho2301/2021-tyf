package com.example.tyfserver.auth.service;

import com.example.tyfserver.auth.dto.IdAndEmail;
import com.example.tyfserver.auth.dto.LoginMember;
import com.example.tyfserver.auth.dto.VerifiedRefundRequest;
import com.example.tyfserver.auth.util.JwtTokenProvider;
import com.example.tyfserver.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtTokenProvider jwtTokenProvider;

    public String createToken(Member member) {
        return jwtTokenProvider.createToken(member.getId(), member.getEmail());
    }

    public String createRefundToken(String merchantUid) {
        return jwtTokenProvider.createRefundToken(merchantUid);
    }

    public LoginMember createLoginMemberByToken(String token) {
        IdAndEmail idAndEmail = jwtTokenProvider.findIdAndEmailFromToken(token);
        return new LoginMember(idAndEmail.getId(), idAndEmail.getEmail());
    }

    public VerifiedRefundRequest createRefundInfoRequestByToken(String token) { // todo: 'RefundInfoRequest' 네이밍 고민해보기!
        String merchantUid = jwtTokenProvider.findMerchantUidFromToken(token);
        return new VerifiedRefundRequest(merchantUid);
    }

    public void validateToken(String token) {
        jwtTokenProvider.validateToken(token);
    }
}
