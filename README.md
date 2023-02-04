# HistoryCrawler
Crawl Vietnam history data with jsoup, display on javafx application
# Team 11
# Member:
Nguyen Hoang Anh

Nguyen Hoang Long

Vu Tien Dung

# How to run
## Step 1: Clone project
## Step 2: Add this configuration to VM configuration
-Dlog4j2.configurationFile="C:\Users\hoang\AnhNguyen\20221\OOP\BaiTapLon\src\resources\log4j.xml" --module-path "javafx-sdk\lib" --add-modules javafx.controls,javafx.fxml
## Step 3: Run project
### Step 3.1: Crawl data
Add to program parameters these if you want to crawl data
DiaDanh for historical destinations
DanhNhan for historical figures
ThoiKy for historical periods
TranDanh for historical wars
SuKien for historical events
For example you can add "DiaDanh DanhNhan ThoiKy TranDanh SuKien" to program parameters
### Step 3.2: Display data
Just run the program without any program parameters is just enough.
