import React, { useState, useRef, useEffect } from 'react';
import axios from 'axios';

const EMOJI = { ROCK: '✊', PAPER: '✋', SCISSORS: '✌️' };

function ElioGame({ onBack }) {
  const [gameState, setGameState] = useState('INIT');
  const [turn, setTurn] = useState('player');
  const [status, setStatus] = useState({ text: 'Configure Game & Press START', result: null });
  
  const timeoutRef = useRef(null);
  useEffect(() => () => clearTimeout(timeoutRef.current), []);

  const initializeGameSeries = () => {
    const settingsPayload = {
      playerName: "Player 1",
      arraySize: 3
    };

    axios.post('http://localhost:8080/api/jack-en-poy/settings', settingsPayload)
      .then(() => {
        setGameState('PLAYING');
        setTurn('player');
        setStatus({ text: 'Your Turn (Round 1)', result: null });
      })
      .catch((err) => {
        console.error("Error setting up game parameters", err);
        setStatus({ text: 'Server Connection Failed', result: null });
      });
  };

  const playJackEnPoy = (userChoice) => {
    if (gameState !== 'PLAYING') return;

    setTurn('computer');
    setStatus({ text: "Computer's Turn...", result: null });

    const movePayload = {
      playerMove: userChoice.toUpperCase() 
    };

    axios.post('http://localhost:8080/api/jack-en-poy/move', movePayload)
      .then((response) => {
        const data = response.data; 

        timeoutRef.current = setTimeout(() => {
          setTurn('player');

          let outcomeMessage = 'ROUND TIE';
          if (data.initialIsVictory === 'VICTORY' || data.initialIsVictory === 'WIN') {
            outcomeMessage = 'YOU WIN ROUND!';
          } else if (data.initialIsVictory === 'DEFEAT' || data.initialIsVictory === 'LOSE') {
            outcomeMessage = 'COMPUTER WINS ROUND';
          }

          setStatus({
            text: null,
            result: { 
              user: data.playerMove, 
              computer: data.computerMove, 
              message: outcomeMessage 
            },
          });

          if (data.isRoundComplete) {
            fetchFinalResults();
          }
        }, 800);
      })
      .catch((err) => {
        console.error("Error executing round turn action", err);
        setTurn('player');
        if (err.response && err.response.status === 400) {
          fetchFinalResults();
        } else {
          setStatus({ text: 'Error executing selection', result: null });
        }
      });
  };

  const fetchFinalResults = () => {
    setGameState('FINISHED');
    axios.get('http://localhost:8080/api/jack-en-poy/final-result')
      .then((response) => {
        const data = response.data;
        
        setStatus({
          text: `MATCH COMPLETE\nOverall: ${data.finalIsVictory}`,
          result: null 
        });
      })
      .catch((err) => {
        console.error("Error retrieving series results logs", err);
        setStatus({ text: 'Error reading final scores', result: null });
      });
  };

  const resetJackEnPoy = () => {
    clearTimeout(timeoutRef.current);
    initializeGameSeries();
  };

  return (
    <div id="app-elio" className="view container-elio active">
      <button className="global-back" onClick={onBack}>← Main Menu</button>
      <div className="handheld-device">
        <div className="screen-outer-frame">
          <div
            id="elio-screen-border"
            className={`screen-inner-display ${turn === 'player' ? 'turn-player' : 'turn-computer'}`}
          >
            <div id="elio-graphics-window" className="game-graphics-frame">
              <div className="pixel-title">
                {gameState === 'INIT' && "JACK EN POY"}
                {gameState === 'PLAYING' && "SERIES ACTIVE"}
                {gameState === 'FINISHED' && "FINAL SCORES"}
              </div>
            </div>
            <div id="elio-status" className="pixel-status" style={{ whiteSpace: 'pre-line' }}>
              {status.text}
              {status.result && (
                <>
                  {EMOJI[status.result.user]} VS {EMOJI[status.result.computer]}
                  <br />
                  {status.result.message}
                </>
              )}
            </div>
          </div>
        </div>
        <div className="device-controls">
          <div className="d-pad">
            <div className="d-pad-vert"></div>
            <div className="d-pad-horiz"></div>
          </div>
          <div className="game-action-pad">
            <button 
              className="circular-action-btn btn-rock" 
              onClick={() => playJackEnPoy('ROCK')}
              disabled={gameState !== 'PLAYING'}
            >
              ✊
            </button>
            <button 
              className="circular-action-btn btn-scissors" 
              onClick={() => playJackEnPoy('SCISSORS')}
              disabled={gameState !== 'PLAYING'}
            >
              ✌️
            </button>
            <button 
              className="circular-action-btn btn-paper" 
              onClick={() => playJackEnPoy('PAPER')}
              disabled={gameState !== 'PLAYING'}
            >
              ✋
            </button>
          </div>
          <div className="device-speaker-grille">
            {Array.from({ length: 10 }).map((_, i) => (
              <span key={i}></span>
            ))}
          </div>
          <div className="system-trigger-row">
            <div className="sys-btn-wrapper">
              <button className="pill-sys-btn" onClick={onBack}></button>
              <label>QUIT</label>
            </div>
            <div className="sys-btn-wrapper">
              <button className="pill-sys-btn" onClick={resetJackEnPoy}></button>
              <label>START</label>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default ElioGame;
