-- 1st Park method to show list of park names
SELECT name FROM park;

--Second Park method to list park details based on selection
SELECT * from park WHERE name = 'Acadia'; --Acadia will be subsituted by the park they select (?)

-- 1st campground method to show all campgrounds based on park selection
SELECT campground_id, name, open_from_mm, open_to_mm, daily_fee FROM campground WHERE park_id = 1; -- 1 will be subsituted for the park they select (?)

-- 1st site method to determine availability of sites
SELECT * 
FROM site
JOIN campground
        ON site.campground_id = campground.campground_id -- 
LEFT JOIN reservation
        ON reservation.site_id = site.site_id
WHERE campground.campground_id = 1 AND site.site_id NOT IN (SELECT site_id FROM reservation WHERE (from_date >= '2018-06-29') AND (to_date <= '2018-06-25')) LIMIT 5;  -- Departure then arrival

SELECT site_number, max_occupancy, accessible, max_rv_length, utilities, daily_fee 
FROM site
JOIN campground
        ON site.campground_id = campground.campground_id -- 
WHERE campground.campground_id = 1 AND site.site_id NOT IN (SELECT site_id FROM reservation WHERE (from_date >= '2018-06-23') AND (to_date <= '2018-06-29')) LIMIT 50;  -- Departure then arrival



--1st reservation method to create a reservation
INSERT INTO reservation (site_id, name, from_date, to_date, create_date)
VALUES (1, 'Gurpreet', '2018-09-18', '2018-09-23', now());
--added by user selection, --added by name on reservation, -- added by start date inputted, --added by end date inputted -- auto created)
