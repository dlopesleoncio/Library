import styled from "styled-components";

export const StyledLivro = styled.div`
    display: block;
    border: 5px;
    border-color: black;
    width: 15.74vw;
    height: 270px;
    font-size: 12px;
    margin:2px;
    align-items: center;
    position:relative;
    @media (max-width:450px){
        width:86px;
        height: 108px;

    }
    &::before {
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5); /* Cor e opacidade do fundo */
    opacity: 0;
    transition: opacity 0.3s ease-in-out;
  }

    &:hover::before {
        box-shadow: 0 0 50px rgba(0, 0, 0, 0.3);
        opacity: 1;
        cursor: pointer;
  }


 
`

export const StyledImage = styled.img`
    width:100%;
    height:100%;


`