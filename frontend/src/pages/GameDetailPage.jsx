import React, { useState, useEffect } from 'react';
import { useParams, Link, useNavigate } from 'react-router-dom';
import axios from 'axios';

function GameDetailPage() {
    const { id } = useParams();
    const navigate = useNavigate();
    const [game, setGame] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    const simulatorRoutes = {
        'FIBONACCI': '/simulator/fibonacci',
        'JACK_EN_POY': '/simulator/jack-en-poy',
        'MULTIPLICATION': '/simulator/multiplication',
        'FACTORIAL': '/simulator/factorial',
        'PASSWORD_VALIDATION': '/simulator/password-validation'
    };

    useEffect(() => {
        axios.get(`http://localhost:8080/api/games/${id}`)
            .then(response => {
                setGame(response.data);
                setLoading(false);
            })
            .catch(err => {
                console.error("Error fetching game detail:", err);
                setError("Game not found or server error.");
                setLoading(false);
            });    
    }, [id]);

    const handleLaunchSimulator = () => {
        const targetRoute = simulatorRoutes[game.gameClass];

        if (targetRoute) {
            navigate(targetRoute);
        } else {
            alert("This simulator engine is still under construction!");
        }
    };

    if (loading) return <p style={{ padding: '40px' }}>Loading game specifications...</p>;
    if (error) return <p style={{ padding: '40ox', color: 'red' }}>{error}</p>;

    return (
        <div style={{ padding: '20px', background: '#fff', borderRadius: '8px', border: '1px solid #ddd' }}>
            <Link to="/" style={{ color: '#666', textDecoration: 'none' }}>← Back to Dashboard</Link>
            <h1 style={{ marginTop: '20px' }}>{game.gameName}</h1>
            <small style={{ color: '#777' }}>Engine Identifier: {game.gameClass}</small>

            <div style={{ margin: '20px 0'}}>
                <span style={{ backgroundColor: '#007bff', color: 'white', padding: '4px 12px', borderRadius: '20px', fontSize: '14px' }}>
                    {game.gameClass}
                </span>
            </div>

            <h3 style={{ marginTop: '30px' }}>Description:</h3>
            <p style={{ lineHeight: '1.6', color: '#444' }}>{game.description}</p>

            <button 
                onClick={handleLaunchSimulator}
                style={{
                padding: '12px 24px',
                background: '#007bff',
                color: 'white',
                border: 'none',
                borderRadius: '5px',
                fontSize: '16px',
                fontWeight: 'bold',
                cursor: 'pointer'
                }}
            >
                🚀 Launch Simulator Engine
            </button>
        </div>
    );
}

export default GameDetailPage;