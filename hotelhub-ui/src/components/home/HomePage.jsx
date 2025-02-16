import React, { useState } from "react";


const HomePage = () => {

    return (
        <div className="home">
            {/* HEADER / BANNER ROOM SECTION */}
            <section>
                <header className="header-banner">
                    <img src="./assets/images/hotel.webp" alt="Phegon Hotel" className="header-image" />
                    <div className="overlay"></div>
                    <div className="animated-texts overlay-content">
                        <h1>
                        Bienvenidos a <span className="phegon-color">HotelHub</span>
                        </h1><br />
                        <h3>Adéntrate en un remanso de comodidad y cuidado</h3>
                    </div>
                </header>
            </section>


            <h4><a className="view-rooms-home" href="#">Todas las habitaciones</a></h4>

            <h2 className="home-services">Servicios en <span className="phegon-color">HotelHub</span></h2>

            {/* SERVICES SECTION */}
            <section className="service-section"><div className="service-card">
                <img src="./assets/images/ac.png" alt="Air Conditioning" />
                <div className="service-details">
                    <h3 className="service-title">Aire acondicionado</h3>
                    <p className="service-description">Manténgase fresco y cómodo durante toda su estadía con nuestro aire acondicionado en la habitación con control individual.</p>
                </div>
            </div>
                <div className="service-card">
                    <img src="./assets/images/mini-bar.png" alt="Mini Bar" />
                    <div className="service-details">
                        <h3 className="service-title">Minibar</h3>
                        <p className="service-description">Disfrute de una conveniente selección de bebidas y bocadillos abastecidos en el minibar de su habitación sin costo adicional.</p>
                    </div>
                </div>
                <div className="service-card">
                    <img src="./assets/images/parking.png" alt="Parking" />
                    <div className="service-details">
                        <h3 className="service-title">Aparcamiento</h3>
                        <p className="service-description">Ofrecemos estacionamiento en el lugar para su comodidad. Pregunte sobre las opciones de servicio de aparcacoches si están disponibles.</p>
                    </div>
                </div>
                <div className="service-card">
                    <img src="./assets/images/wifi.png" alt="WiFi" />
                    <div className="service-details">
                        <h3 className="service-title">WiFi</h3>
                        <p className="service-description">Manténgase conectado durante toda su estadía con el acceso gratuito a Wi-Fi de alta velocidad disponible en todas las habitaciones y áreas públicas.</p>
                    </div>
                </div>

            </section>
          
            <section>

            </section>
        </div>
    );
}

export default HomePage;