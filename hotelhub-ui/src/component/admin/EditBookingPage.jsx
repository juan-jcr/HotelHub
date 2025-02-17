import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import ApiService from '../../service/ApiService'; // Assuming your service is in a file called ApiService.js

const EditBookingPage = () => {
    const navigate = useNavigate();
    const { bookingCode } = useParams();
    const [bookingDetails, setBookingDetails] = useState(null); // State variable for booking details
    const [error, setError] = useState(null); // Track any errors
    const [success, setSuccessMessage] = useState(null); // Track any errors



    useEffect(() => {
        const fetchBookingDetails = async () => {
            try {
                const response = await ApiService.getBookingByConfirmationCode(bookingCode);
                setBookingDetails(response.booking);
            } catch (error) {
                setError(error.message);
            }
        };

        fetchBookingDetails();
    }, [bookingCode]);


    const acheiveBooking = async (bookingId) => {
        if (!window.confirm('Are you sure you want to Acheive this booking?')) {
            return; // Do nothing if the user cancels
        }

        try {
            const response = await ApiService.cancelBooking(bookingId);
            if (response.message ===  "successful") {
                setSuccessMessage("The boking was Successfully Acheived")
                
                setTimeout(() => {
                    setSuccessMessage('');
                    navigate('/admin/manage-bookings');
                }, 3000);
            }
        } catch (error) {
            setError(error.response?.data?.message || error.message);
            setTimeout(() => setError(''), 5000);
        }
    };

    return (
        <div className="find-booking-page">
            <h2>Detalle de la reserva</h2>
            {error && <p className='error-message'>{error}</p>}
            {success && <p className='success-message'>{success}</p>}
            {bookingDetails && (
                <div className="booking-details">
                    <h3>Detalles de la reserva</h3>
                    <p>Código de confirmación: {bookingDetails.bookingConfirmationCode}</p>
                    <p>Fecha de entrada: {bookingDetails.checkInDate}</p>
                    <p>Fecha de entrada: {bookingDetails.checkOutDate}</p>
                    <p>N° de adultos: {bookingDetails.numOfAdults}</p>
                    <p>N° de niños: {bookingDetails.numOfChildren}</p>
                    <p>Correo electrónico del huésped: {bookingDetails.guestEmail}</p>

                    <br />
                    <hr />
                    <br />
                    <h3>Detalles</h3>
                    <div>
                        <p> Nombre: {bookingDetails.user.name}</p>
                        <p> Email: {bookingDetails.user.email}</p>
                        <p> Teléfono: {bookingDetails.user.phoneNumber}</p>
                    </div>

                    <br />
                    <hr />
                    <br />
                    <h3>Detalles de la habitación</h3>
                    <div>
                        <p> Tipo de pieza: {bookingDetails.room.roomType}</p>
                        <p> Precio: ${bookingDetails.room.roomPrice}</p>
                        <p> Descripción: {bookingDetails.room.roomDescription}</p>
                        <img src={bookingDetails.room.roomPhotoUrl} alt="" sizes="" srcSet="" />
                    </div>
                    <button
                        className="acheive-booking"
                        onClick={() => acheiveBooking(bookingDetails.id)}>Cerrar reserva
                    </button>
                </div>
            )}
        </div>
    );
};

export default EditBookingPage;
