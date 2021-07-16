package com.example.tyfserver;

import com.example.tyfserver.auth.domain.Oauth2Type;
import com.example.tyfserver.banner.domain.Banner;
import com.example.tyfserver.banner.domain.BannerRepository;
import com.example.tyfserver.donation.domain.Donation;
import com.example.tyfserver.donation.repository.DonationRepository;
import com.example.tyfserver.member.domain.Member;
import com.example.tyfserver.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!prod")
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final MemberRepository memberRepository;
    private final BannerRepository bannerRepository;
    private final DonationRepository donationRepository;

    @Override
    public void run(String... args) throws Exception {
        Member roki = memberRepository
            .save(new Member("Rok93@naver.com", "로키", "rokiMountain", Oauth2Type.NAVER));
        Member suri = memberRepository
            .save(new Member("DWL5@kakao.com", "수리", "surisuriMahasuri", Oauth2Type.KAKAO));
        Member bePoz = memberRepository
            .save(new Member("Be-poz@google.com", "파즈", "allIsBePozzible", Oauth2Type.GOOGLE));
        Member joy = memberRepository
            .save(new Member("Joykim@naver.com", "조이", "enjoyLife", Oauth2Type.NAVER));
        Member hwano = memberRepository
            .save(new Member("jho2301@kakao.com", "파노", "hwanorama", Oauth2Type.KAKAO));
        Member inch = memberRepository
            .save(new Member("hchayan@google.com", "인치", "1inch", Oauth2Type.GOOGLE));

        bannerRepository.save(new Banner(roki, "roki-image.png"));
        bannerRepository.save(new Banner(suri, "suri-image.png"));
        bannerRepository.save(new Banner(joy, "joy-image.png"));
        bannerRepository.save(new Banner(bePoz, "bePoz-image.png"));
        bannerRepository.save(new Banner(hwano, "hwano-image.png"));

        Donation donation1 = new Donation(100_000L);
        donation1.addMessage("포비", "이 친구 코드 잘 짜네", true);
        donation1.to(roki);

        Donation donation2 = new Donation(100_000_000L);
        donation2.addMessage("제이슨", "이 정도는 제 연봉의 1/5 밖에 안된다구요!", true);
        donation2.to(bePoz);

        Donation donation3 = new Donation(9_999L);
        donation3.addMessage("구구", "9999999999", true);
        donation3.to(joy);

        Donation donation4 = new Donation(9_999L);
        donation4.addMessage("구구", "99999999", true);
        donation4.to(joy);

        Donation donation5 = new Donation(9_999L);
        donation5.addMessage("구구", "999999", true);
        donation5.to(joy);

        Donation donation6 = new Donation(10_000L);
        donation6.addMessage("포코", "당근의 세계에선.... 그 누구도 평등하죠", true);
        donation6.to(hwano);

        Donation donation7 = new Donation(50_000L);
        donation7.addMessage("공원", "갈.대.공.원", true);
        donation7.to(inch);

        Donation donation8 = new Donation(10_000L);
        donation8.addMessage("브라운", "조기수료 시켜드릴게요~!", true);
        donation8.to(suri);

        donationRepository.save(donation1);
        donationRepository.save(donation2);
        donationRepository.save(donation3);
        donationRepository.save(donation4);
        donationRepository.save(donation5);
        donationRepository.save(donation6);
        donationRepository.save(donation7);
        donationRepository.save(donation8);
    }
}
