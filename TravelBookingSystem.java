import java.util.Scanner;
public class TravelBookingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookingController bookingController = new BookingController();

        System.out.println("Ласкаво просимо до системи онлайн-бронювання подорожей!");

        while (true) {
            System.out.print("Введіть рейс: ");
            String flight = scanner.nextLine();

            // Перевірка рейсу
            if (!bookingController.checkFlightAvailability(flight)) {
                System.out.println("Вибраний рейс не існує. Спробуйте ще раз.");
                continue;
            }

            String hotel;
            while (true) {
                System.out.print("Введіть готель: ");
                hotel = scanner.nextLine();

                // Перевірка готелю
                if (bookingController.checkHotelAvailability(hotel)) {
                    break;
                } else {
                    System.out.println("Готель не знайдено або недоступний. Спробуйте ще раз.");
                }
            }

            System.out.print("Введіть екскурсію: ");
            String excursions = scanner.nextLine();

            TravelPackage travelPackage = bookingController.createTravelPackage(flight, hotel, excursions);

            System.out.println("Перевіряємо доступність...");
            if (bookingController.checkHotelAvailability(hotel)) {
                bookingController.bookTravelPackage(travelPackage);
            } else {
                System.out.println("На жаль, у вибраному готелі немає доступних місць.");
            }

            System.out.println("Чи бажаєте скасувати бронювання готелю? (так/ні)");
            String cancelResponse = scanner.nextLine();
            if (cancelResponse.equalsIgnoreCase("так")) {
                bookingController.cancelHotelBooking(hotel);
            }

            System.out.println("Чи бажаєте завершити програму? (так/ні)");
            String exitResponse = scanner.nextLine();
            if (exitResponse.equalsIgnoreCase("так")) {
                break;
            }
        }

        scanner.close();
    }
}
