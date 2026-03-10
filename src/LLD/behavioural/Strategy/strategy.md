# When **Strategy Pattern** Needed ?

Suppose you are building a Payment System.
<br>
Without **Strategy Pattern**, you write something like:

```java
public void pay(String type, double amount) {
    if (type.equalsIgnoreCase("CREDIT_CARD")) {
        System.out.println("Paid with Credit Card");
    } else if (type.equalsIgnoreCase("UPI")) {
        System.out.println("Paid with UPI");
    } else if (type.equalsIgnoreCase("PAYPAL")) {
        System.out.println("Paid with PayPal");
    } else if (type.equalsIgnoreCase("CRYPTO")) {
        System.out.println("Paid with Bitcoin");
    }
    // and so on...
}
```

## 🚨 Problems start when...

1. ✅ You need to support 10, 20, 50 payment types...
2. ✅ Every time you add a new type, you open this method and add one more else if.
3. ✅ This method becomes longer, uglier, riskier.

If one developer wrongly edits here, it can break everything!

This is called if-else hell.

## 🎯 Now, What is Strategy Pattern Solution?

Instead of writing all logic inside one method,
<br>
you split each payment method into separate small classes.

```java
public interface PaymentStrategy {
    void pay(double amount);
}

public class CreditCardPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("Paid " + amount + " by Credit Card");
    }
}

public class UpiPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("Paid " + amount + " by UPI");
    }
}

public class PaypalPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("Paid " + amount + " by PayPal");
    }
}
```

🏗 How Code Looks Now (Client Side)
Instead of writing many if-else,
client code only does:

```java
PaymentStrategy strategy = getPaymentStrategyFromUserInput(); // returns CreditCardPayment or UpiPayment etc.
strategy.pay(1000);
```

✅ No huge if-else
<br>
✅ Code is clean
<br>
✅ Adding new payment types is super easy

```pgsql
Before (If-Else Hell):
[Business Logic]
   |
   |--> if (CREDIT_CARD) { Logic }
   |--> else if (UPI) { Logic }
   |--> else if (PAYPAL) { Logic }
   |--> else if (CRYPTO) { Logic }
   |--> ...

------------------------------------

After (Strategy Pattern):
[Business Logic]
    |
    |--> [PaymentStrategy Interface] ---> [Selected Payment Class at Runtime]
```

## 📜 FINAL Short Summary (Interview Style):

If-Else Hell = When a method grows huge with many if-else conditions.

Strategy Pattern = Move each option/logic into separate classes.

The Context class only calls the Strategy Interface — it doesn't care which logic is inside.

✅ Cleaner code
<br>
✅ Easy to add new types
<br>
✅ Follows Open/Closed Principle

---

---

## 🛑 Problem with current simple pay(double amount) interface

The current PaymentStrategy interface:

```java
void pay(double amount);
```

Only supports one argument.

Cannot handle special fields like upiId, cardNumber, etc.

### 🏗 Solution 1: Create a PaymentRequest Object (Recommended)

✅ You create a common class that contains all possible fields needed.

```java
public class PaymentRequest {
    private double amount;
    private String upiId;
    private String cardNumber;
    private String expiryDate;
    private String cvv;
    private String emailAddress;

    // getters, setters, constructors
}
```

✅ Then your Strategy Interface becomes:

```java
public interface PaymentStrategy {
    void pay(PaymentRequest request);
}
```

✅ And each payment strategy reads only the fields it needs.

Example:

```java
public class UpiPayment implements PaymentStrategy {
    @Override
    public void pay(PaymentRequest request) {
        System.out.println("Paid " + request.getAmount() + " using UPI ID: " + request.getUpiId());
    }
}

public class CreditCardPayment implements PaymentStrategy {
    @Override
    public void pay(PaymentRequest request) {
        System.out.println("Paid " + request.getAmount() + " using Credit Card: " + request.getCardNumber());
    }
}

```

## If PaymentRequest class keeps adding new fields (applePayId, cryptoWalletId, etc.), Does it violate SOLID? 🤔

### 🎯 Quick Answer:

✅ It does NOT violate Strategy Pattern.
<br>
✅ But if you bloat PaymentRequest too much, it can start violating Single Responsibility.
(because it becomes a "God class" — knows about every possible field for all payments.)
<br>
✅ So we need to design it smartly if the system grows bigger.

### 🛠 Professional Solutions (for Big Systems)

1. **Split PaymentRequest into Multiple Specialized Requests**
   <br>
   ✅ Instead of ONE bloated PaymentRequest,
   <br>
   you make **specialized request classes**:
   ```java
   public interface PaymentRequest {
    double getAmount();
   }
   ```
   Then :
   ```java
   public class UpiPaymentRequest implements PaymentRequest {
       private double amount;
       private String upiId;
       // getters/setters
   }
   
   public class CreditCardPaymentRequest implements PaymentRequest {
       private double amount;
       private String cardNumber;
       private String expiryDate;
       private String cvv;
       // getters/setters
   }
   
   public class ApplePayPaymentRequest implements PaymentRequest {
       private double amount;
       private String applePayId;
       // getters/setters
   }
   ```
2. **Each PaymentStrategy Accepts Its Specific Request**
   Modify interface:
   ```java
   public interface PaymentStrategy<T extends PaymentRequest> { 
        void pay(T request);
   }
   ```
   Example:
   ```java 
   public class UpiPayment implements PaymentStrategy<UpiPaymentRequest> {
       @Override
       public void pay(UpiPaymentRequest request) {
           System.out.println("Paid " + request.getAmount() + " via UPI ID: " + request.getUpiId());
       }
   }
   
   public class ApplePayPayment implements PaymentStrategy<ApplePayPaymentRequest> {
       @Override
       public void pay(ApplePayPaymentRequest request) {
           System.out.println("Paid " + request.getAmount() + " via Apple Pay ID: " + request.getApplePayId());
       }
   }
   ```

