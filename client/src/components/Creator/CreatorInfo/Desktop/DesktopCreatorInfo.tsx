import {
  DescriptionContainer,
  InfoContainer,
  NickName,
  ProfileImg,
  StyledCreatorInfo,
  StyledInfo,
  StyledButton,
} from './DesktopCreatorInfo.styles';

interface Props {
  defaultUserProfile: string;
  nickname: string;
  profileImage: string;
  isAdmin: boolean;
  shareUrl: () => void;
  popupDonationAmountPage: () => void;
}

const DesktopCreatorInfo = ({
  defaultUserProfile,
  nickname,
  profileImage,
  isAdmin,
  shareUrl,
  popupDonationAmountPage,
}: Props) => {
  return (
    <StyledCreatorInfo>
      <ProfileImg src={profileImage ?? defaultUserProfile} />
      <InfoContainer>
        <StyledInfo>
          <NickName>{nickname}</NickName>
          {isAdmin ? (
            <StyledButton onClick={shareUrl}>내 페이지 공유하기</StyledButton>
          ) : (
            <StyledButton onClick={popupDonationAmountPage}>후원하기</StyledButton>
          )}
        </StyledInfo>

        <DescriptionContainer>
          <p>제 페이지에 와주셔서 감사합니다!!</p>
        </DescriptionContainer>
      </InfoContainer>
    </StyledCreatorInfo>
  );
};

export default DesktopCreatorInfo;