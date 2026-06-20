import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

function AdminAddGame() {
    const navigate = useNavigate(); 
    const [formData, setFormData] = useState({ gameName: '', description: '', gameClass: '' });
    const [submitError, setSubmitError] = useState(null);

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData(prev => ({ ...prev, [name]: value}));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        setSubmitError(null);

        if (!formData.gameName || !formData.description || !formData.gameClass) {
            setSubmitError("All fields are required.");
            return;
        }

    };

    return (
        <div>
            <h1 style={{ color: '#d9534f' }}>🔒 Admin Portal: Add New Game</h1>
            <hr />
            
            <div style={{ background: '#fff9f9', padding: '20px', borderRadius: '8px', border: '1px solid #ecc' }}>
                {submitError && <p style={{ color: 'red', fontSize: '14px' }}>{submitError}</p>}
                
                <form onSubmit={handleSubmit} style={{ display: 'flex', flexDirection: 'column', gap: '12px' }}>
                <div>
                    <label style={{ display: 'block', marginBottom: '4px', fontWeight: 'bold' }}>Game Name:</label>
                    <input 
                    type="text" name="gameName" value={formData.gameName} onChange={handleInputChange}
                    style={{ width: '100%', padding: '8px', borderRadius: '4px', border: '1px solid #ccc' }}
                    />
                </div>

                <div>
                    <label style={{ display: 'block', marginBottom: '4px', fontWeight: 'bold' }}>Description:</label>
                    <textarea 
                    name="description" value={formData.description} onChange={handleInputChange}
                    style={{ width: '100%', padding: '8px', borderRadius: '4px', border: '1px solid #ccc', minHeight: '60px' }}
                    />
                </div>

                <div>
                    <label style={{ display: 'block', marginBottom: '4px', fontWeight: 'bold' }}>Game Class:</label>
                    <select name="gameClass" value={formData.gameClass} onChange={handleInputChange}
                    style={{ width: '100%', padding: '8px', borderRadius: '4px', border: '1px solid #ccc' }}
                    >
                        <option value="">-- Select Class --</option>
                        <option value="FIBONACCI">FIBONACCI</option>
                        <option value="JACK_EN_POY">JACK_EN_POY</option>
                        <option value="FACTORIAL">FACTORIAL</option>
                        <option value="MULTIPLICATION">MULTIPLICATION</option>
                        <option value="PASSWORD_VALIDATION">PASSWORD_VALIDATION</option>
                    </select>
                </div>

                <button type="submit" style={{ padding: '10px', background: '#d9534f', color: 'white', border: 'none', borderRadius: '4px', cursor: 'pointer', fontWeight: 'bold' }}>
                    Publish Game to Database
                </button>
                </form>
            </div>
        </div>
    );
    
}

export default AdminAddGame;
