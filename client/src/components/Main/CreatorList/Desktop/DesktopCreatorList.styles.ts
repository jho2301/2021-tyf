import styled from 'styled-components';

export const List = styled.ul`
  box-sizing: content-box;
  padding: 1.5rem;
  display: flex;
  justify-content: center;
  align-items: center;
  overflow-x: scroll;
  -ms-overflow-style: none; // ms
  scrollbar-width: none; // firefox

  &::-webkit-scrollbar {
    display: none;
  }

  & li {
    margin: 0 1rem;
  }
`;