# RaaProjekat
An application made in Kotlin for the college course held by Levi9, RAA.
# Restaurants
The app fetches a list of restaurants from an API and relevant info pertaining to them, and displays them in a RecyclerView.


Using the coordinates gotten from the API, it forwards them to a [Reverse Geocoding API](https://nominatim.openstreetmap.org/ui/search.html) from which it gets a corresponding address.


The app utilizes [Android Room](https://developer.android.com/training/data-storage/room),
an abstraction over SQLite for storing user info, including the email, password and misc. info.

