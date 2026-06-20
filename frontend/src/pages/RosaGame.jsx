import React, { useState, useRef, useEffect } from 'react';
import axios from 'axios';

function RosaGame({ onBack, formData = {}, navigate, setSubmitError }) {
  const [messages, setMessages] = useState([
    { from: 'rosa', text: "Rosa: Let's test your strength entry!" },
  ]);
  const [input, setInput] = useState('');
  const chatRef = useRef(null);

  useEffect(() => {
    if (chatRef.current) {
      chatRef.current.scrollTop = chatRef.current.scrollHeight;
    }
  }, [messages]);

  const sendRosaMessage = () => {
    const secureText = input;
    if (secureText.trim() === '') return;

    const masked = '•'.repeat(secureText.length);
    setMessages((prev) => [...prev, { from: 'user', text: masked }]);
    setInput('');

    setTimeout(() => {
      let responseReport = 'Weak password! Please use variations of characters.';
      let isPerfect = false;

      if (secureText.length >= 8) {
        const hasNum = /\d/.test(secureText);
        const hasSpecial = /[!@#$%^&*(),.?":{}|<>_]/.test(secureText);
        
        if (hasNum && hasSpecial) {
          responseReport = 'Perfect secure password configuration! Processing registration...';
          isPerfect = true;
        } else if (hasNum || hasSpecial) {
          responseReport = 'Medium security threat mitigation level. Upgrade structure.';
        }
      }

      setMessages((prev) => [...prev, { from: 'rosa', text: responseReport }]);
      if (isPerfect) {
        const authRequest = {
          username: formData.username || "",
          password: secureText,
          accountType: formData.accountType || "USER"
        };

        axios.post('http://localhost:8080/api/auth/register', authRequest)
          .then((response) => {
            const data = response.data;
            
            if (data && data.isSuccessful) {
              if (navigate) navigate('/');
            } else {
              setMessages((prev) => [
                ...prev, 
                { from: 'rosa', text: 'Rosa: Registration rejected by the server system.' }
              ]);
            }
          })
          .catch((err) => {
            console.error("Error to register", err);
            const backendMessage = err.response?.data?.message || "Failed to register.";
            
            if (setSubmitError) setSubmitError(backendMessage);
            
            setMessages((prev) => [
              ...prev, 
              { from: 'rosa', text: `Registration Error: ${backendMessage}` }
            ]);
          });
      }
    }, 600);
  };

  const handleKeyDown = (e) => {
    if (e.key === 'Enter') sendRosaMessage();
  };

  return (
    <div id="app-rosa" className="view container-rosa active">
      <button className="global-back" onClick={onBack}>← Main Menu</button>
      
      <div className="desktop-window-frame rosa-theme-window">
        <div className="window-title-bar">
          <div className="sidebar-branding">PASSWORD SIMULATION</div>
          <div className="window-system-actions">
            <span className="win-min"></span>
            <span className="win-max"></span>
            <span className="win-close" onClick={onBack}></span>
          </div>
        </div>
        
        <div className="window-split-layout">
          <div className="left-bar-starburst">
            <div className="starburst-container">
              <span className="star-accent ext-1">✦</span>
              <div className="vertical-text-title">PASSWORD SIMULATION</div>
              <span className="star-accent ext-2">✦</span>
            </div>
          </div>
          
          <div className="right-chat-feed">
            <div id="chat-messages" className="scrollable-chat-area" ref={chatRef}>
              {messages.map((m, i) => (
                <div key={i} className={`msg-bubble ${m.from === 'rosa' ? 'msg-rosa' : 'msg-user'}`}>
                  {m.text}
                </div>
              ))}
            </div>
            
            <div className="docked-action-input">
              <input
                type="text"
                id="rosa-input"
                placeholder="Type password here..."
                value={input}
                onChange={(e) => setInput(e.target.value)}
                onKeyDown={handleKeyDown}
              />
              <button onClick={sendRosaMessage}>SEND</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default RosaGame;
