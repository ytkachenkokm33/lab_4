import java.util.HashMap;
import java.util.Map;


class BookingController {
    private Map<String, Integer> hotelAvailability = new HashMap<>();
    private Map<String, String> flightDatabase = new HashMap<>();

    public BookingController() {
        // Емуляція бази даних доступності готелів
        hotelAvailability.put("Готель Ritz Paris", 2);
        hotelAvailability.put("Готель Hilton Kyiv", 5);

        // Емуляція бази даних рейсів: номер рейсу -> авіакомпанія
        flightDatabase.put("1", "МАУ");
        flightDatabase.put("2", "Lufthansa");
        flightDatabase.put("3", "Turkish Airlines");
    }

    public TravelPackage createTravelPackage(String flight, String hotel, String excursions) {
        System.out.println("[BookingController] Починаємо створення туристичного пакета...");

        // Використання патерну Builder
        TravelPackage travelPackage = new TravelPackage.Builder()
                .setFlight(flight)
                .setHotel(hotel)
                .setExcursions(excursions)
                .build();

        System.out.println("[BookingController] Туристичний пакет створено: " + travelPackage);
        return travelPackage;
    }

    public boolean checkHotelAvailability(String hotel) {
        int available = hotelAvailability.getOrDefault(hotel, -1);
        if (available == -1) {
            System.out.println("[BookingController] Готель не знайдено: " + hotel);
            return false;
        }
        System.out.println("[BookingController] Перевірка доступності: " + hotel + " (доступно місць: " + available + ")");
        return available > 0;
    }

    public boolean checkFlightAvailability(String flight) {
        if (flightDatabase.containsKey(flight)) {
            System.out.println("[BookingController] Рейс доступний: " + flight + " (Авіакомпанія: " + flightDatabase.get(flight) + ")");
            return true;
        } else {
            System.out.println("[BookingController] Рейс не знайдено: " + flight);
            return false;
        }
    }

    public void bookHotel(String hotel) {
        if (hotelAvailability.getOrDefault(hotel, 0) > 0) {
            hotelAvailability.put(hotel, hotelAvailability.get(hotel) - 1);
            System.out.println("[BookingController] Готель заброньовано: " + hotel);
        } else {
            System.out.println("[BookingController] Усі місця в готелі зайняті: " + hotel);
        }
    }

    public void cancelHotelBooking(String hotel) {
        hotelAvailability.put(hotel, hotelAvailability.getOrDefault(hotel, 0) + 1);
        System.out.println("[BookingController] Бронювання готелю скасовано: " + hotel);
        System.out.println("[BookingController] Оновлена доступність: " + hotel + " (доступно місць: " + hotelAvailability.get(hotel) + ")");
    }

    public void bookTravelPackage(TravelPackage travelPackage) {
        System.out.println("[BookingController] Бронюємо туристичний пакет...");
        System.out.println("[BookingController] Інформація про пакет: " + travelPackage);

        String hotel = travelPackage.getHotel();
        if (checkHotelAvailability(hotel)) {
            bookHotel(hotel);
            System.out.println("[BookingController] Реєструємо екскурсії...");
            System.out.println("[BookingController] Бронювання успішне!");
        } else {
            System.out.println("[BookingController] Не вдалося забронювати пакет. Готель недоступний.");
        }
    }
}



