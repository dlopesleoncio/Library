import React from 'react';
import './App.css';
import { useState,useEffect } from 'react';
import Form from './components/Form';
import Livro from './components/Livro';
import { StyledNav,StyledInput } from './components/styles/Nav.Styled';

function App() {

  const [livros, setLivros] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const params = {
          query: 'python',
        };
        const queryString = new URLSearchParams(params).toString();
        const response = await fetch(`/api/livros`);
        const data = await response.json();
        setLivros(data);
      } catch (error) {
        console.error(error);
      }
    };

    fetchData();
  }, []);

  return (
    <div className="App">
      <StyledNav>
        <StyledInput type="search"></StyledInput>
      </StyledNav>
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
