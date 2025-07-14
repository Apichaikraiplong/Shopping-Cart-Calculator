package Lib;

import java.util.ArrayList;

public class ShoppingCartCalculator {

    /**
     * เมธอดนี้ทำการคำนวณราคาสินค้าในตะกร้า โดยหนึ่งสินค้าประกอบด้วย {รหัสการซื้อ , ชื่อสินค้า , ราคาต่อชิ้น , จำนวนที่ซื้อ}
     * ถ้า items เป็น null หรือ empty คือ ไม่มีสินค้าชิ้นนี้ หรือ ไม่มีสินค้าชิ้นนี้เหลืออยู่
     * ถ้า CartItem มี price หรือ quantity ติดลบ โค้ตจะเกิดการ ERROR
     * กฎส่วนลด ประกอบด้วยสามอย่าง { BOGO (ซื้อ 1 แถม 1) , BULK (ซื้อ >= 6 ชิ้น ลด 10%) , NORMAL (ซื้อสินค้าปกติไม่อยู่ในเงื่อนไขของ BOGO และ BULK) }
     * @param totalprice คือ ราคาที่ต้องการตรวจสอบ
     * @return ส่วนลดทีประกอบด้วย {์NORMAL , BOGO , BULK}
     */
    public static double calculateTotalPrice(ArrayList<CartItem> items)  {
        
        if(items == null || items.isEmpty())
            return 0.0;

        double total = 0.0;

        for (CartItem Item : items) {
            if(Item == null || Item.price() < 0 || Item.quantity() < 0){
                throw new IllegalArgumentException("ERROR");
            }
            
            double itemTotal = 0.0;
            String sku = Item.sku();
            double price = Item.price();
            int quantity = Item.quantity();

            switch (sku.toUpperCase()) {
                case "BOGO":
                    int pay = (quantity/2) + (quantity%2);
                        itemTotal = price * pay;
                    break;

                case "BULK":
                    if (quantity >= 6) {
                        itemTotal = price * quantity * 0.9 ;
                    } 
                    else{
                        itemTotal = price * quantity ;
                    }
                    break;

                case "NORMAL":
                        itemTotal = price * quantity ;
                    break;
                default:
                        itemTotal = price * quantity ;
            }

            total += itemTotal;

        }
        return total;

        
    }
}
