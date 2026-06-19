import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';

function GameDashboard() {
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
    <div>
      <h1>My Game System Dash</h1>
      <hr />
      {loading && <p>Connecting to Spring Boot backend API...</p>}
      {error && <p style={{ color: 'red' }}>{error}</p>}

      {!loading && !error && games.length === 0 && <p>No games available.</p>}

      {!loading && !error && games.length > 0 && (
        <table style={{ width: '100%', borderCollapse: 'collapse', marginTop: '20px' }}>
          <thead>
            <tr style={{ backgroundColor: '#f4f4f4', textAlign: 'left' }}>
              <th style={{ padding: '12px', borderBottom: '2px solid #ddd' }}>ID</th>
              <th style={{ padding: '12px', borderBottom: '2px solid #ddd' }}>Game Name</th>
              <th style={{ padding: '12px', borderBottom: '2px solid #ddd' }}>Description</th>
              <th style={{ padding: '12px', borderBottom: '2px solid #ddd' }}>Class</th>
            </tr>
          </thead>
          <tbody>
            {games.map((game) => (
              <tr key={game.id} style={{ borderBottom: '1px solid #ddd' }}>
                <td style={{ padding: '12px' }}>{game.id}</td>
                <td style={{ padding: '12px', fontWeight: 'bold' }}>
                    <Link
                        to={`/games/${game.id}`}
                        style={{ color: '#007bff', textDecoration: 'none', fontWeight: 'bold' }}
                        onMouseOver={(e) => e.target.style.textDecoration = 'underline'}
                        onMouseOut={(e) => e.target.style.textDecoration = 'none'}
                    >
                        {game.gameName}
                    </Link>
                </td>
                <td style={{ padding: '12px' }}>{game.description}</td>
                <td style={{ padding: '12px' }}>
                  <span style={{ backgroundColor: '#e0e0e0', padding: '4px 8px', borderRadius: '4px', fontSize: '12px' }}>
                    {game.gameClass}
                  </span>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}

export default GameDashboard;
