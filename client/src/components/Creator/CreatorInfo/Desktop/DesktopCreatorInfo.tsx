import DefaultProfileImg from '../../../../assets/images/default-user-profile.png';
import { Creator } from '../../../../types';
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
  creator: Creator;
  isAdmin: boolean;
  shareUrl: () => void;
  popupDonationAmountPage: () => void;
}

const DesktopCreatorInfo = ({ creator, isAdmin, shareUrl, popupDonationAmountPage }: Props) => {
  return (
    <StyledCreatorInfo>
      <ProfileImg src={creator.profileImage || DefaultProfileImg} />
      <InfoContainer>
        <StyledInfo>
          <NickName>{creator.nickname}</NickName>
          {isAdmin ? (
            <StyledButton onClick={shareUrl}>내 페이지 공유하기</StyledButton>
          ) : (
            <StyledButton onClick={popupDonationAmountPage}>후원하기</StyledButton>
          )}
        </StyledInfo>

        <DescriptionContainer>
          <p>{creator.bio}</p>
        </DescriptionContainer>
      </InfoContainer>
    </StyledCreatorInfo>
  );
};

export default DesktopCreatorInfo;
