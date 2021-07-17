import { useEffect } from 'react';
import { useHistory, useParams } from 'react-router-dom';
import { ParamTypes } from '../../../App';
import useCreator from '../../../service/hooks/useCreator';
import useDonation from '../../../service/hooks/useDonation';

import {
  CloseButton,
  CreatorRouteButton,
  EmojiText,
  MainText,
  StyledTemplate,
  SubText,
  SuccessButtonContainer,
  SuccessMessageContainer,
} from './DonationSuccessPage.styles';

const DonationSuccessPage = () => {
  const history = useHistory();
  const { creatorId } = useParams<ParamTypes>();
  const { nickname } = useCreator(creatorId);
  const { donation } = useDonation(creatorId);

  const closeWindow = () => {
    window.close();
    (open('auto:blank', '_self') as Window).close();
  };

  return (
    <StyledTemplate>
      <SuccessMessageContainer>
        <SubText>{nickname}님에게</SubText>
        <MainText>{donation.amount}원</MainText>
        <SubText>후원되었습니다.</SubText>
        <EmojiText>🎉</EmojiText>
      </SuccessMessageContainer>
      <SuccessButtonContainer>
        <CreatorRouteButton onClick={() => history.push(`/creator/${creatorId}`)}>
          🏠 창작자 페이지로 놀러가기
        </CreatorRouteButton>
        <CloseButton onClick={closeWindow}>창 닫기</CloseButton>
      </SuccessButtonContainer>
    </StyledTemplate>
  );
};

export default DonationSuccessPage;
