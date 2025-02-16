// src/ProtectedRoute.js
import React from 'react';
import { Navigate, useLocation } from 'react-router-dom';
import AuthService from './AuthService';


export const ProtectedRoute = ({ element: Component }) => {
  const location = useLocation();

  return AuthService.isAuthenticated() ? (
    Component
  ) : (
    <Navigate to="/login" replace state={{ from: location }} />
  );
};


export const AdminRoute = ({ element: Component }) => {
  const location = useLocation();

  return AuthService.isAdmin() ? (
    Component
  ) : (
    <Navigate to="/login" replace state={{ from: location }} />
  );
};