import React, { useState } from 'react';
import axios from 'axios';

function LilaGame({ onBack }) {
  const [buffer, setBuffer] = useState('');
  const [tableEntries, setTableEntries] = useState([]);
  const [errorMessage, setErrorMessage] = useState('');

  const pressLilaKey = (num) => {
    setBuffer((prev) => prev + num);
  };

  const generateLilaTable = () => {
    if (buffer === '') return;

    const parsedNum = parseInt(buffer, 10);
    setErrorMessage('');

    const requestPayload = {
      num1: parsedNum
    };

    axios.post('http://localhost:8080/api/multiplication/table', requestPayload)
      .then((response) => {
        const data = response.data;

        if (data && data.isSuccessful) {
          setTableEntries(data.tableEntries || []);
        } else {
          setErrorMessage('System error generating multiplication matrix.');
        }
      })
      .catch((err) => {
        console.error("Error to call multiplication API", err);
        setErrorMessage('Failed to connect to the backend system.');
      })
      .finally(() => {
        setBuffer('');
      });
  };

  return (
    <div id="app-lila" className="view container-lila active">
      <button className="global-back" onClick={onBack}>← Main Menu</button>
      
      <div className="lila-math-terminal">
        <div className="terminal-left-paper">
          <div id="lila-paper" className="ruled-paper-output">
            {errorMessage && <div className="error-text">{errorMessage}</div>}

            {tableEntries.map((entry, i) => (
              <React.Fragment key={i}>
                {`${entry.base} X ${entry.multiplier} = ${entry.product}`}
                <br />
              </React.Fragment>
            ))}
          </div>
        </div>
        
        <div className="terminal-right-controls">
          <div id="lila-input-screen" className="calc-display-window">{buffer}</div>
          <div className="calc-button-grid">
            {['1', '2', '3', '4', '5', '6', '7', '8', '9'].map((n) => (
              <button key={n} onClick={() => pressLilaKey(n)}>{n}</button>
            ))}
            <button className="calc-heart-btn">💜</button>
            <button onClick={() => pressLilaKey('0')}>0</button>
            <button className="calc-enter-btn" onClick={generateLilaTable}>ENTER</button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default LilaGame;
