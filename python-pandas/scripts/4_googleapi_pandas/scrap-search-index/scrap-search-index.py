from pathlib import Path
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support import expected_conditions
from selenium.webdriver.support.wait import WebDriverWait


# to install python: brew install python
# pip3 install scrapy
# pip3 install selenium


def scrape_url_google_maps(url):
    print('Opening headless browser for url: ', url)
    options = webdriver.ChromeOptions()
    # options.add_argument('headless')

    # open chrome://version/ and copy Profile Path:
    # e.g. /Users/[username]]/Library/Application Support/Google/Chrome/Default
    profile_path = str(Path.home()) + '/Library/Application Support/Google/Chrome/Default'
    print('Profile path = ', profile_path)
    options.add_argument('--user-data-dir=' + profile_path)
    options.add_experimental_option("detach", True)  # leave browser open for debug

    browser = webdriver.Chrome(options=options)
    browser.get(url)

    _timeout = 10  # ⚠ don't forget to set a reasonable timeout
    WebDriverWait(browser, _timeout).until(
        expected_conditions.presence_of_element_located(
            # we can wait by any selector type like element id:
            (By.ID, "operations-tag-Auth")
            # or by class name
            # (By.CLASS_NAME, ".price")
            # or by xpath
            # (By.XPATH, "//h1[@class='price']")
            # or by CSS selector
            # (By.CSS_SELECTOR, "h1.price")
        )
    )

    print('Reading data...')
    # review titles / username / Person who reviews
    review_titles = browser.find_elements(By.CLASS_NAME, 'section-review-title')
    print([a.text for a in review_titles])

    # review text / what did they think
    review_text = browser.find_elements(By.CLASS_NAME, 'section-review-review-content')
    print([a.text for a in review_text])

    # get the number of stars
    # stars = browser.find_elements(By.CLASS_NAME, 'section-review-stars')
    # first_review_stars = stars[0]
    # active_stars = first_review_stars.find_elements(By.CLASS_NAME, 'section-review-star-active')
    # print(f"the stars the first review got was {len(active_stars)}")


scrape_url_google_maps(
    'https://www.google.com/maps/place/The+Crown+%26+Two+Chairmen/@51.5129825,-0.136811,16.54z/data=!4m10!1m2!2m1!1ssoho+pubs!3m6!1s0x487604d3216692f5:0xb45ed4b5f14d22ed!8m2!3d51.5138608!4d-0.1324646!15sCglzb2hvIHB1YnNaCyIJc29obyBwdWJzkgEDcHVimgEjQ2haRFNVaE5NRzluUzBWSlEwRm5TVU13ZWtseFVWbFJFQUXgAQA!16s%2Fm%2F051tcs9?entry=ttu'
)