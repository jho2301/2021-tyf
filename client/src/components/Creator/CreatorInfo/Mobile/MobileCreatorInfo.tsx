import Profile from '../../Profile/Profile';
import { DescriptionContainer, StyledButton, StyledCreatorInfo } from './MobileCreatorInfo.styles';

interface Props {
  isAdmin: boolean;
  shareUrl: () => void;
  popupDonationAmountPage: () => void;
}

const MobileCreatorInfo = ({ isAdmin, shareUrl, popupDonationAmountPage }: Props) => {
  return (
    <StyledCreatorInfo>
      <Profile />
      <DescriptionContainer>
        <p>제 페이지에 와주셔서 감사합니다!!</p>
      </DescriptionContainer>
      {isAdmin ? (
        <StyledButton onClick={shareUrl}>내 페이지 공유하기</StyledButton>
      ) : (
        <StyledButton onClick={popupDonationAmountPage}>후원하기</StyledButton>
      )}
    </StyledCreatorInfo>
  );
};

export default MobileCreatorInfo;