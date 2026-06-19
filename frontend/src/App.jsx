import React, { useState, useEffect } from 'react';
import axios from 'axios';

function App() {
  const [games, setGames] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    axios.get('http://localhost:8080/api/games')
    .then(response => {
      setGames(response.data);
      setLoading(false);
    })
    .catch(err => {
      console.error("Error connecting to Spring Boot", err);
      setError("Could not connect to the backend server.");
      setLoading(false);
    });
  }, []);

  return (
    <div style={{ padding: '40px', fontFamily: 'Arial, sans-serif' }}>
      <h1> My Game System Dash</h1>
      <hr />

      {loading && <p>Connecting to Spring Boot backend API...</p>}
      {error && <p style={{ color: 'red' }}>{error}</p>}

      {!loading && !error && (
        <div>
          <h3>Data received successfully:</h3>
          {/* This prints your backend data cleanly on the screen */}
          <pre style={{ background: '#f4f4f4', padding: '15px', borderRadius: '5px' }}>
            {JSON.stringify(games, null, 2)}
          </pre>
        </div>
      )}
    </div>
  );
}

export default App;