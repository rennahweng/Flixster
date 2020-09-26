# Flixster
Flixster is a movie browsing app that allows users to browse movies from the list of movies extracted from The Movie Database API (http://docs.themoviedb.apiary.io/#).

📝 `NOTE - PASTE PART 2 SNIPPET HERE:` Paste the README template for part 2 of this assignment here at the top. This will show a history of your development process, which users stories you completed and how your app looked and functioned at each step.

---

## Flixster Part 1

### User Stories 


#### REQUIRED (10pts)
- [x] (10pts) User can view a list of movies (title, poster image, and overview) currently playing in theaters from the Movie Database API.

#### BONUS
- [x] (2pts) Views should be responsive for both landscape/portrait mode.
   - [x] (1pt) In portrait mode, the poster image, title, and movie overview is shown.
   - [x] (1pt) In landscape mode, the rotated alternate layout should use the backdrop image instead and show the title and movie overview to the right of it.

- [x] (2pts) Display a nice default [placeholder graphic](https://guides.codepath.org/android/Displaying-Images-with-the-Glide-Library#advanced-usage) for each image during loading
- [x] (2pts) Improved the user interface by experimenting with styling and coloring.
   - [x] Changed app icon to a more colorful and cuter logo! (shown in gif)
   - [x] Modified the app theme’s Primary, Dark Primary and Accent Colors.
   - [x] Styled movie posters to have rounded corners using Glide transformations.
   - [x] Styled each listed movie view to have some margins in between each other, so it's easier for user's eyes to browse.
   
- [ ] (2pts) For popular movies (i.e. a movie voted for more than 5 stars), the full backdrop image is displayed. Otherwise, a poster image, the movie title, and overview is listed. Use Heterogenous RecyclerViews and use different ViewHolder layout files for popular movies and less popular ones.

### App Walkthough GIF
<img src="https://github.com/rennahweng/Flixster/blob/master/walkthroughs/portrait.gif" alt="Portrait mode" width=300><br>
<br>
<img src="https://github.com/rennahweng/Flixster/blob/master/walkthroughs/landscape.gif" alt="Landscape mode" width=600><br>
<br>
GIF created with <a href="https://www.cockos.com/licecap/">LICEcap</a>.

### Notes
I tried to style the list view by adding some 

### Open-source libraries used

- [Android Async HTTP](https://github.com/codepath/CPAsyncHttpClient) - Simple asynchronous HTTP requests with JSON parsing
- [Glide](https://github.com/bumptech/glide) - Image loading and caching library for Androids
