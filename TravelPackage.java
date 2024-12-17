class TravelPackage {
    private String flight;
    private String hotel;
    private String excursions;

    // Приватний конструктор для використання Будівельником
    private TravelPackage(Builder builder) {
        this.flight = builder.flight;
        this.hotel = builder.hotel;
        this.excursions = builder.excursions;
    }

    public String getHotel() {
        return hotel;
    }

    public String getFlight() {
        return flight;
    }

    @Override
    public String toString() {
        return "TravelPackage{" +
                "flight='" + flight + '\'' +
                ", hotel='" + hotel + '\'' +
                ", excursions='" + excursions + '\'' +
                '}';
    }

    // Статичний внутрішній клас Builder
    public static class Builder {
        private String flight;
        private String hotel;
        private String excursions;

        public Builder setFlight(String flight) {
            this.flight = flight;
            return this;
        }

        public Builder setHotel(String hotel) {
            this.hotel = hotel;
            return this;
        }

        public Builder setExcursions(String excursions) {
            this.excursions = excursions;
            return this;
        }

        public TravelPackage build() {
            return new TravelPackage(this);
        }
    }
}

