package com.example.tyfserver.donation.service;

import com.example.tyfserver.auth.domain.Oauth2Type;
import com.example.tyfserver.donation.domain.Donation;
import com.example.tyfserver.donation.domain.Message;
import com.example.tyfserver.donation.dto.DonationMessageRequest;
import com.example.tyfserver.donation.dto.DonationRequest;
import com.example.tyfserver.donation.dto.DonationResponse;
import com.example.tyfserver.donation.exception.DonationNotFoundException;
import com.example.tyfserver.donation.repository.DonationRepository;
import com.example.tyfserver.member.domain.Member;
import com.example.tyfserver.member.exception.MemberNotFoundException;
import com.example.tyfserver.member.repository.MemberRepository;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DonationServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private DonationRepository donationRepository;

    @InjectMocks
    private DonationService donationService;

    @Test
    @DisplayName("createDonation Test")
    public void createDonationTest() {
        //given
        DonationRequest request = new DonationRequest("pageName", 1000L);
        //when
        when(memberRepository.findByPageName(Mockito.anyString()))
                .thenReturn(
                        Optional.of(new Member("email", "nickname", "pagename", Oauth2Type.GOOGLE)));
        when(donationRepository.save(Mockito.any(Donation.class)))
                .thenReturn(new Donation(1L, 1000L, Message.defaultMessage()));
        //then
        DonationResponse response = donationService.createDonation(request);
        assertThat(response).usingRecursiveComparison()
            .ignoringFields("createdAt")
            .isEqualTo(new DonationResponse(1L, Message.DEFAULT_NAME, Message.DEFAULT_MESSAGE, 1000L, LocalDateTime.now()));
    }

    @Test
    @DisplayName("createDonation member not found Test")
    public void createDonationNotFoundTest() {
        //given
        DonationRequest request = new DonationRequest("pageName", 1000L);
        //when
        when(memberRepository.findByPageName(Mockito.anyString()))
                .thenReturn(Optional.empty());
        //then
        assertThatThrownBy(() -> donationService.createDonation(request))
                .isInstanceOf(MemberNotFoundException.class);
    }

    @Test
    @DisplayName("addMessageToDonation Test")
    public void addMessageToDonationTest() {
        //given
        Donation givenDonation = new Donation(1L, 100L, Message.defaultMessage());
        DonationMessageRequest request = new DonationMessageRequest("changedName", "changedMessage", false);
        //when
        when(donationRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(
                        givenDonation
                ));
        //then
        donationService.addMessageToDonation(1L, request);
        assertThat(givenDonation.getName()).isEqualTo("changedName");
        assertThat(givenDonation.getMessage()).isEqualTo("changedMessage");
    }

    @Test
    @DisplayName("addMessageToDonation donation not found Test")
    public void addMessageToDonationNotFoundTest() {
        //given
        Donation givenDonation = new Donation(1L, 100L, Message.defaultMessage());
        DonationMessageRequest request = new DonationMessageRequest("changedName", "changedMessage", false);
        //when
        when(donationRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.empty());
        //then
        assertThatThrownBy(() -> donationService.addMessageToDonation(1L, request))
                .isInstanceOf(DonationNotFoundException.class);
    }

    @Test
    @DisplayName("findMyDonation Test")
    public void findMyDonationTest() {
        //given
        PageRequest reqeust = PageRequest.of(0, 1);
        //when
        when(memberRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(new Member("email", "nickname", "pagename", Oauth2Type.GOOGLE)));
        when(donationRepository.findDonationByMemberOrderByCreatedAtDesc(Mockito.any(Member.class), Mockito.any(Pageable.class)))
                .thenReturn(Collections.singletonList(new Donation(1L, 1000L, Message.defaultMessage())));
        //then
        List<DonationResponse> response = donationService.findMyDonations(1L, reqeust);
        assertThat(response.get(0).getName()).isEqualTo(Message.DEFAULT_NAME);
        assertThat(response.get(0).getMessage()).isEqualTo(Message.DEFAULT_MESSAGE);
    }

    @Test
    @DisplayName("findMyDonation member not found Test")
    public void findMyDonationMemberNotFoundTest() {
        //given
        PageRequest reqeust = PageRequest.of(0, 1);
        //when
        when(memberRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.empty());
        //then
        assertThatThrownBy(() -> donationService.findMyDonations(1L, reqeust))
                .isInstanceOf(MemberNotFoundException.class);
    }

    @Test
    @DisplayName("findPublicDonation Test")
    public void findPublicDonationTest() {
        //given
        //when
        when(memberRepository.findByPageName(Mockito.anyString()))
                .thenReturn(Optional.of(new Member("email", "nickname", "pagename", Oauth2Type.GOOGLE)));
        when(donationRepository.findFirst5ByMemberOrderByCreatedAtDesc(Mockito.any(Member.class)))
                .thenReturn(
                        Collections.singletonList(new Donation(1L, 1000L, new Message("name", "message", true))));
        //then
        List<DonationResponse> response = donationService.findPublicDonations("pagename");
        assertThat(response.get(0).getName()).isEqualTo(Message.SECRET_NAME);
        assertThat(response.get(0).getMessage()).isEqualTo(Message.SECRET_MESSAGE);
    }

    @Test
    @DisplayName("findPublicDonation member not found Test")
    public void findPublicDonationMemberNotFoundTest() {
        //given
        //when
        when(memberRepository.findByPageName(Mockito.anyString()))
                .thenReturn(Optional.empty());
        //then
        assertThatThrownBy(() -> donationService.findPublicDonations("pagename"))
                .isInstanceOf(MemberNotFoundException.class);
    }
}