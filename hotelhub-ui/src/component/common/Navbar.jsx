import React from 'react';
import { NavLink, useNavigate } from 'react-router-dom';
import ApiService from '../../service/ApiService';

function Navbar() {
    const isAuthenticated = ApiService.isAuthenticated();
    const isAdmin = ApiService.isAdmin();
    const isUser = ApiService.isUser();
    const navigate = useNavigate();

    const handleLogout = () => {
        const isLogout = window.confirm('Are you sure you want to logout this user?');
        if (isLogout) {
            ApiService.logout();
            navigate('/home');
        }
    };

    return (
        <nav className="navbar">
            <div className="navbar-brand">
                <NavLink to="/home">HotelHub</NavLink>
            </div>
            <ul className="navbar-ul">
                <li><NavLink to="/home" activeclassname="active">INICIO</NavLink></li>
                <li><NavLink to="/rooms" activeclassname="active">HABITACIONES</NavLink></li>
                <li><NavLink to="/find-booking" activeclassname="active">BUSCAR MI RESERVA</NavLink></li>

                {isUser && <li><NavLink to="/profile" activeclassname="active">PERFL</NavLink></li>}
                {isAdmin && <li><NavLink to="/admin" activeclassname="active">ADMIN</NavLink></li>}

                {!isAuthenticated &&<li><NavLink to="/login" activeclassname="active">LOGIN</NavLink></li>}
                {!isAuthenticated &&<li><NavLink to="/register" activeclassname="active">SIGN UP</NavLink></li>}
                {isAuthenticated && <li onClick={handleLogout}>SALIR</li>}
            </ul>
        </nav>
    );
}

export default Navbar;
