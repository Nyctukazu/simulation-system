import React, { useState } from 'react';
import axios from 'axios';

function BueGame({ onBack }) {
  const [input, setInput] = useState('');
  const [output, setOutput] = useState('Ready to check number...');

  const checkFibonacciBackend = () => {
    if (input === '' || input < 0) {
      setOutput('Enter a valid number >= 0');
      return;
    }

    const parsedNumber = parseFloat(input);


    const requestPayload = {
      number: parsedNumber
    };

    axios.post('http://localhost:8080/api/fibonacci/check', requestPayload)
      .then((response) => {
        const data = response.data;

        if (data) {
          const statusText = data.isFibonacci 
            ? "IS a valid Fibonacci number! 🌀" 
            : "is NOT a Fibonacci number. ❌";

          setOutput(
            <>
              The number {data.checkedNumber}
              <br />
              <span style={{ fontSize: '1.5rem', color: data.isFibonacci ? '#4caf50' : '#f44336' }}>
                {statusText}
              </span>
            </>
          );
        }
      })
      .catch((err) => {
        console.error("Error communicating with Fibonacci API", err);
        setOutput('Server Error: Failed to verify number.');
      })
      .finally(() => {
        setInput(''); 
      });
  };

  const handleKeyDown = (e) => {
    if (e.key === 'Enter') checkFibonacciBackend();
  };

  return (
    <div id="app-bue" className="view container-bue active">
      <button className="global-back" onClick={onBack}>← Main Menu</button>
      <div className="desktop-window-frame bue-theme-window">
        <div className="window-title-bar">
          <div className="window-tab-active"><span className="tab-icon">🐱</span></div>
          <div className="window-system-actions">
            <span className="win-min"></span>
            <span className="win-max"></span>
            <span className="win-close" onClick={onBack}></span>
          </div>
        </div>
        <div className="window-workspace-body">
          <h1 className="logo-text-fibo">FIBONACCI <span className="logo-sub">CHECKER 🌀</span></h1>
          <div className="browser-navigation-pills">
            <span className="pill-nav active">ALL</span>
            <span className="pill-nav">IMAGES</span>
            <span className="pill-nav">VIDEOS</span>
          </div>
          <div className="search-input-composite">
            <button className="magnifier-btn" onClick={checkFibonacciBackend}>🔍</button>
            <input
              type="number"
              id="fib-input"
              placeholder="Check if number belongs..."
              value={input}
              onChange={(e) => setInput(e.target.value)}
              onKeyDown={handleKeyDown}
            />
          </div>
          <div className="output-canvas-box">
            <div id="fib-output" className="dynamic-inner-results">{output}</div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default BueGame;
