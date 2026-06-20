import React, { useState } from 'react';
import axios from 'axios';

function OliverGame({ onBack }) {
  const [buffer, setBuffer] = useState('');
  const [display, setDisplay] = useState('Enter a number & hit !');

  const calculateFactorial = (currentBuffer) => {
    if (currentBuffer === '') return;

    const num = parseInt(currentBuffer, 10);
    
    if (isNaN(num) || num < 0) {
      setDisplay('Error: Invalid Input');
      return;
    }

    const requestPayload = {
      num1: num
    };

    axios.post('http://localhost:8080/api/factorial/calculate', requestPayload)
      .then((response) => {
        const data = response.data;
        
        if (data) {
          setDisplay(`${data.inputNumber}! =\n${data.product}`);
        }
      })
      .catch((err) => {
        console.error("Error calculating factorial via backend", err);
        
        if (err.response && err.response.status === 400) {
          setDisplay('Num too large!\nMax input is 20.');
        } else {
          setDisplay('Server Error:\nFailed to connect.');
        }
      })
      .finally(() => {
        setBuffer('');
      });
  };

  const pressOliverKey = (value) => {
    if (value === '!') {
      calculateFactorial(buffer);
      return;
    }
    const newBuffer = buffer + value;
    setBuffer(newBuffer);
    setDisplay(newBuffer);
  };

  const clearOliver = () => {
    setBuffer('');
    setDisplay('Enter value & hit calculate ♡');
  };

  return (
    <div id="app-oliver" className="view container-oliver active">
      <button className="global-back" onClick={onBack}>← Main Menu</button>
      <div className="clamshell-phone">
        <div className="phone-hinge-top">
          <div className="phone-screen-matte">
            <div id="oliver-display" className="phone-screen-content" style={{ whiteSpace: 'pre-line' }}>
              {display}
            </div>
          </div>
          <div className="phone-top-accent-heart">♥</div>
        </div>
        <div className="phone-hinge-bottom">
          <div className="phone-navigation-cluster">
            <button className="phone-side-meta" onClick={clearOliver}>⇤</button>
            <button className="phone-central-heart" onClick={() => calculateFactorial(buffer)}>♥</button>
            <button className="phone-side-meta" onClick={onBack}>➔</button>
          </div>
          <div className="phone-matrix-keypad">
            {['1', '2', '3', '4', '5', '6', '7', '8', '9'].map((n) => (
              <button key={n} onClick={() => pressOliverKey(n)}>{n}</button>
            ))}
            <div className="dino-sticker-wrapper">
              <div className="dino-body">🦕</div>
            </div>
            <button onClick={() => pressOliverKey('0')}>0</button>
            <button onClick={() => pressOliverKey('!')}>!</button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default OliverGame;
