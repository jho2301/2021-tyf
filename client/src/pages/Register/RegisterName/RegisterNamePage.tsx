import { FC, HTMLAttributes } from 'react';
import Anchor from '../../../components/@atom/Anchor/Anchor';

import Button from '../../../components/@atom/Button/Button';
import InputWithMessage from '../../../components/@molecule/InputWithMessage/InputWithMessage';
import { NameInputContainer, RegisterNameTitle, StyledTemplate } from './RegisterNamePage.styles';

const RegisterNamePage: FC<HTMLAttributes<HTMLElement>> = () => {
  return (
    <StyledTemplate>
      <RegisterNameTitle>
        당신의
        <br /> 닉네임은
        <br /> 무엇인가요?
      </RegisterNameTitle>
      <NameInputContainer>
        <InputWithMessage
          isSuccess={false}
          successMessage="좋은 닉네임이네요!"
          failureMessage="이미 존재하는 닉네임이에요... 다른 닉네임으로 시도해주세요!"
          placeholder="닉네임 입력하기"
        />
      </NameInputContainer>
      <Button disabled>
        <Anchor to="/register/success">회원가입 완료</Anchor>
      </Button>
    </StyledTemplate>
  );
};

export default RegisterNamePage;
