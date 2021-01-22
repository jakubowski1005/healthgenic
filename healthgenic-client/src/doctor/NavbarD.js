import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { Button } from "../components/Button";
import AuthService from "../services/AuthService";
import "../components/Navbar.css";

function NavbarD() {
  const [click, setClick] = useState(false);
  const [button, setButton] = useState(true);

  const handleClick = () => setClick(!click);
  const closeMobileMenu = () => setClick(false);

  const showButton = () => {
    if (window.innerWidth <= 960) {
      setButton(false);
    } else {
      setButton(true);
    }
  };

  useEffect(() => {
    showButton();
  }, []);

  window.addEventListener("resize", showButton);

  return (
    <>
      <nav className="navbar">
        <div className="navbar-container">
          <Link to="/doctor" className="navbar-logo" onClick={closeMobileMenu}>
            HealthGenic
            <i class="fas fa-heartbeat"></i>
          </Link>
          <div className="menu-icon" onClick={handleClick}>
            <i className={click ? "fas fa-times" : "fas fa-bars"} />
          </div>
          <ul className={click ? "nav-menu active" : "nav-menu"}>
            <li className="nav-item">
              <Link
                to="/doctor"
                className="nav-links"
                onClick={closeMobileMenu}
              >
                Profile
              </Link>
            </li>
            <li className="nav-item">
              <Link
                to="/patientList"
                className="nav-links"
                onClick={closeMobileMenu}
              >
                Patient list
              </Link>
            </li>
            <li className="nav-item">
              <Link
                to="/messagesD"
                className="nav-links"
                onClick={closeMobileMenu}
              >
                Messages
              </Link>
            </li>
            <li>
              <Link
                to="/"
                className="nav-links-mobile"
                onClick={closeMobileMenu}
                onClick={AuthService.logout}
              >
                Sign out
              </Link>
            </li>
          </ul>
          {button && <Button buttonStyle="btn--outline">SIGN OUT</Button>}
        </div>
      </nav>
    </>
  );
}

export default NavbarD;
