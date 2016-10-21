/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 * <p>
 * package com.example.android.justjava;
 */
package com.example.android.jusjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.jusjava.R;

import static android.R.attr.checked;
import static android.view.View.Y;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText nameField = (EditText) findViewById(R.id.name_field);
        String name = nameField.getText().toString();
        CheckBox hasWhippedCream = (CheckBox) findViewById(R.id.whipped_cream);
        boolean checkWhippedCream = hasWhippedCream.isChecked();
        CheckBox hasChocolateTopping = (CheckBox) findViewById(R.id.chocolate_topping);
        boolean checkChocolateTopping = hasChocolateTopping.isChecked();
        String coffees = "Name:" + name;
        coffees += "\nAdd whipped cream? " + checkWhippedCream;
        coffees += "\nAdd Chocolate Topping? " + checkChocolateTopping;
        coffees += "\nTotal count of coffees = " + quantity + "\n Price=$" + (calculatePrice(checkWhippedCream, checkChocolateTopping));
        coffees += "\nThank you!\nPlease visit again";
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Jus Java order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT, coffees);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


    /**
     * This method calculates the price
     *
     * @return total price
     */
    public int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        int baseprice = 5;
        if (addWhippedCream)
            baseprice = baseprice + 1;
        if (addChocolate)
            baseprice = baseprice + 3;
        return quantity * baseprice;
    }

    /**
     * This method is called when the + button is clicked.
     */
    public void increment(View view) {
        if (quantity == 100) {
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        display(quantity);
    }

    /**
     * This method is called when the - button is clicked.
     */
    public void decrement(View view) {
        if (quantity == 1) {
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity - 1;
        display(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);

    }

}
