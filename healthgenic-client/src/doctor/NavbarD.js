import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { Button } from "../components/Button";
import { register, login, logout } from "../services/AuthService";
import "../components/Navbar.css";

function NavbarD() {
  const [click, setClick] = useState(false);
  const [button, setButton] = useState(true);

  const handleClick = () => setClick(!click);
  const closeMobileMenu = () => setClick(false);

  const logoutBtnClick = () => logout();

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
              <Link to="/login" className="nav-links" onClick={logoutBtnClick}>
                Sign out
              </Link>
            </li>
          </ul>
        </div>
      </nav>
    </>
  );
}

export default NavbarD;
