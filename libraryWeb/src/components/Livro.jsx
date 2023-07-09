
import { StyledLivro,StyledImage } from "./styles/Livro.Styled"
export default function Livro({livro}){
    
    
    const livroPath = livro.imgpath
    console.log(livro)
    return(
        
            <StyledLivro>
                <StyledImage src={livroPath}/>
            </StyledLivro>

    )
        
}