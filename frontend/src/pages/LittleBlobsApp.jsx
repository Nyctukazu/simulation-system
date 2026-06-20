import React, { useState } from 'react';
import ElioGame from './ElioGame';
import BueGame from './BueGame';
import OliverGame from './OliverGame';
import RosaGame from './RosaGame';
import LilaGame from './LilaGame';
import '../LittleBlobsApp.css';

const GAMES = [
  { id: 'app-elio', title: 'Elio', badge: 'JACK EN POY', cardClass: 'card-elio', img: '../assets/elio.png', alt: 'Elio' },
  { id: 'app-bue', title: 'Bue', badge: 'FIBONACCI FINDER', cardClass: 'card-bue', img: '../assets/beu.png', alt: 'Bue' },
  { id: 'app-oliver', title: 'Oliver', badge: 'FACTORIAL', cardClass: 'card-oliver', img: '../assets/olive.png', alt: 'Olive' },
  { id: 'app-rosa', title: 'Rosa', badge: 'PASSWORD SIMULATOR', cardClass: 'card-rosa', img: '../assets/rosa.png', alt: 'Rosa' },
  { id: 'app-lila', title: 'Lila', badge: 'MULTIPLICATION TABLE', cardClass: 'card-lila', img: '../assets/lila.png', alt: 'Lila' },
];

function LittleBlobsApp() {
  const [view, setView] = useState('main-menu');

  const goHome = () => setView('main-menu');

  return (
    <div>
      {view === 'main-menu' && (
        <div id="main-menu" className="view active">
          <header className="menu-header">
            <div className="star-group left-stars">
              <span className="star-large">✦</span>
              <span className="star-small">✦</span>
            </div>

            <h1>Little Blobs</h1>

            <div className="star-group right-stars">
              <span className="star-large">✦</span>
              <span className="star-small">✦</span>
            </div>
          </header>

          <div className="blob-grid">
            {GAMES.map((game) => (
              <div
                key={game.id}
                className={`blob-card ${game.cardClass}`}
                onClick={() => setView(game.id)}
              >
                <h2 className="card-title">{game.title}</h2>
                <div className="app-title-badge">{game.badge}</div>
                <div className="character-container">
                  <img src={game.img} alt={game.alt} className="blob-img-asset" />
                </div>
              </div>
            ))}
          </div>
        </div>
      )}

      {view === 'app-elio' && <ElioGame onBack={goHome} />}
      {view === 'app-bue' && <BueGame onBack={goHome} />}
      {view === 'app-oliver' && <OliverGame onBack={goHome} />}
      {view === 'app-rosa' && <RosaGame onBack={goHome} />}
      {view === 'app-lila' && <LilaGame onBack={goHome} />}
    </div>
  );
}

export default LittleBlobsApp;
