package com.example.tyfserver.member.domain;

import com.example.tyfserver.auth.domain.Oauth2Type;
import com.example.tyfserver.banner.domain.Banner;
import com.example.tyfserver.common.domain.BaseTimeEntity;
import com.example.tyfserver.donation.domain.Donation;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String nickname;

    private String profileImage;

    @Embedded
    private Point point;

    @Enumerated(EnumType.STRING)
    private Oauth2Type oauth2Type;

    @Column(unique = true)
    private String pageName;

    @OneToMany(mappedBy = "member")
    private final List<Banner> banners = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private final List<Donation> donations = new ArrayList<>();

    public Member(String email, String nickname, String pageName, Oauth2Type oauth2Type) {
        this.email = email;
        this.nickname = nickname;
        this.pageName = pageName;
        this.oauth2Type = oauth2Type;
        this.point = new Point(0L);
        this.profileImage = null;
    }

    public Member(String email, String nickname, String pageName, Oauth2Type oauth2Type, String profileImage) {
        this.email = email;
        this.nickname = nickname;
        this.pageName = pageName;
        this.oauth2Type = oauth2Type;
        this.point = new Point(0L);
        this.profileImage = profileImage;
    }

    public void addDonation(final Donation donation) {
        this.donations.add(donation);
        donation.to(this);
        addPoint(donation.getAmount());
    }

    private void addPoint(final long donationAmount) {
        this.point.add(donationAmount);
    }

    public long getPoint() {
        return this.point.getPoint();
    }

    public boolean isSameOauthType(String type) {
        return this.oauth2Type.name().equals(type);
    }

    public void uploadProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public void deleteProfile() {
        this.profileImage = null;
    }
}