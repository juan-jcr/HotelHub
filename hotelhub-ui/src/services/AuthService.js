import axios from "axios"

export default class AuthService {

    static BASE_URL = "http://localhost:8080"

    

    /**AUTH */

    /* This  register a new user */
    static async registerUser(registration) {
        const response = await axios.post(`${this.BASE_URL}/auth/register`, registration)
        return response.data
    }

    /* This  login a registered user */
    static async loginUser(loginDetails) {
        const response = await axios.post(`${this.BASE_URL}/auth/login`, loginDetails)
        return response.data
    }



    static isAuthenticated() {
        const token = localStorage.getItem('token')
        return !!token
    }

    /**AUTHENTICATION CHECKER */
    static logout() {
        localStorage.removeItem('token')
        localStorage.removeItem('role')
    }
    
    static isAdmin() {
        const role = localStorage.getItem('role')
        return role === 'ADMIN'
    }
    static isUser() {
        const role = localStorage.getItem('role')
        return role === 'USER'
    }



}