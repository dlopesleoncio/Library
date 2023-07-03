import React from 'react';
import './App.css';
import { useState,useEffect } from 'react';
import Form from './components/Form';
import Livro from './components/Livro';
import { StyledNav,StyledInput } from './components/styles/Nav.Styled';
import DropdownNav from './components/Dropdown';
function App() {
  const [livros, setLivros] = useState([]);
  const [queryText,setQuery] = useState('')
  
  

  useEffect(() => {
    const fetchData = async () => {
      try {
        //const params = {
        //  query: queryText,
//};
       // const queryString = new URLSearchParams(params).toString();
        const response = await fetch(`http://localhost:8080/api/livros/nomes?query=${queryText}`);
        const data = await response.json();
        setLivros(data);
      } catch (error) {
        console.error(error);
      }
    };
    if(queryText){
      fetchData();
    }
    
  }, [queryText]);

  const handleInputChange = (event) => {
    setQuery(event.target.value);
  };

  return (
    <div className="App">
      <StyledNav>
        <h3 className='profile'>Profile</h3>
        <StyledInput type="text" value={queryText} onChange={handleInputChange} placeholder="Search by name"></StyledInput>
      </StyledNav>

      {/*
      <StyledNav>
        <h3 className='profile'>Profile</h3>
        <StyledInput type="text" value={queryText} onChange={handleInputChange} placeholder="Search by name"></StyledInput>
      </StyledNav>      
      
      */}
      <div className="dropdown"><DropdownNav/></div>
      

      <div>
        <div className="card-container">
          {
            livros.map((livro,index) => {return <Livro livro = {livro}/>}) 
          }
        </div>
      </div>
      
    </div>
  );
}

export default App;
