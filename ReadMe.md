1. Наповнення бази відбувається у паралельному потоці який запускається після старту програми.
2. Звертання до API "https://cex.io/api/last_price/{symbol1}/{symbol2}" відбувається кожних три секунди, якщо ціна змінилася - вона записується у БД, а якщо ціна не змінилася, то не записується.

   ```
    @EventListener(ApplicationReadyEvent.class)
    public void getDataAndWriteToDB() {
        Thread thread = new Thread(() -> {
            while (true) {
                writeToDB().write("BTC", "USD");
                writeToDB().write("ETH", "USD");
                writeToDB().write("XRP", "USD");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
   ```
