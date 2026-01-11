-- SQLite
SELECT language_name, letter, SUM(amount) AS amount_of_letters, SUM(amount * amount) AS squared_amount_of_letters
FROM Pages
INNER JOIN LettersAmount ON Pages.page_id = LettersAmount.page_id
WHERE language_name = 'en'
GROUP BY language_name, letter;

