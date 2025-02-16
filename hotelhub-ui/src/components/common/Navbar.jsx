import React from 'react';
import { NavLink, useNavigate } from 'react-router-dom';
import AuthService from '../../services/AuthService';

function Navbar() {
    const isAuthenticated = AuthService.isAuthenticated();
    const isAdmin = AuthService.isAdmin();
    const isUser = AuthService.isUser();
    const navigate = useNavigate();

    const handleLogout = () => {
        const isLogout = window.confirm('Are you sure you want to logout this user?');
        if (isLogout) {
            AuthService.logout();
            navigate('/home');
        }
    };

    return (
        <nav className="navbar">
            <div className="navbar-brand">
                <NavLink to="/home">HotelHub</NavLink>
            </div>
            <ul className="navbar-ul">
                <li><NavLink to="/home" activeclassname="active">Home</NavLink></li>
                <li><NavLink to="/rooms" activeclassname="active">Dormitorios</NavLink></li>
                <li><NavLink to="/find-booking" activeclassname="active">Buscar mi reserva</NavLink></li>

                {isUser && <li><NavLink to="/profile" activeclassname="active">Perfil</NavLink></li>}
                {isAdmin && <li><NavLink to="/admin" activeclassname="active">Admin</NavLink></li>}

                {!isAuthenticated &&<li><NavLink to="/login" activeclassname="active">Login</NavLink></li>}
                {!isAuthenticated &&<li><NavLink to="/register" activeclassname="active">Register</NavLink></li>}
                {isAuthenticated && <li onClick={handleLogout}>Salir</li>}
            </ul>
        </nav>
    );
}

export default Navbar;