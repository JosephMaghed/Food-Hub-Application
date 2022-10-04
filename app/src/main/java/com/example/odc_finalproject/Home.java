package com.example.odc_finalproject;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.ActionMode;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
    ArrayList<ProductModel> productModels = new ArrayList<>();
    EditText Search;
ImageButton menuBtn;
ActionMode actionMode;
ImageView BURGER;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
public static int c=0;
LinearLayout page,menu;
    String[] productNames = {"Macdonalds","Pizza Hut","Arabiata","Papa Johns","Shear Box","Cheese lovers","Oriental Dish","Margerita"};
    String[] Prices = {"$4.99","2$","$4.99","$4.99","5$","6$","$4.99","2.5$"};
    String[] LongDesc = {"Bananas are nutritious. Bananas may be good for weight loss. apples may be good for your heart. As part of a healthful and varied diet.",
            "Red pepper can be used to cook sauce and many more recipes",
            "2 kilos of beef bones",
            "One and half kilo of pure chicken",
            "Bananas are nutritious. Bananas may be good for weight loss. apples may be good for your heart. As part of a healthful and varied diet.",
            "Red pepper can be used to cook sauce and many more recipes"," "," "};
    String[] productDesc = {"7pcs, Priceg","0.5 kilo pepper","1kg, Priceg","1kg, Priceg","7pcs, Priceg","0.5 kilo pepper"};

    int[] productImages = {R.drawable.mac,R.drawable.pizzahut,R.drawable.arabiata,
            R.drawable.papajohns,R.drawable.grand_share,R.drawable.pizzafood,R.drawable.spaghetti,R.drawable.margerita};

    Product_RecyclerViewAdapter adapter;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    public NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BURGER=findViewById(R.id.imageView7);
        gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc= GoogleSignIn.getClient(this,gso);
        BURGER.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Home.this,Filters.class);
                startActivity(i);
            }
        });
        Search=findViewById(R.id.EDSearch) ;
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Home.this,Search.class);
                startActivity(i);
            }
        });
        menuBtn=findViewById(R.id.SideMenu);



        // drawer layout instance to toggle the menu icon to open
        // drawer and back button to close drawer
        drawerLayout = findViewById(R.id.H);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        navigationView =findViewById(R.id.NavigatioView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.fav_food:
                        Toast.makeText(Home.this, "Favorite Food", Toast.LENGTH_LONG).show();
                        Intent f=new Intent(Home.this,FavoriteFood.class);
                        startActivity(f);
                        return true;
                    case R.id.orders:
                        Toast.makeText(Home.this, "MY Orders", Toast.LENGTH_LONG).show();
                        Intent o=new Intent(Home.this,Orders.class);
                        startActivity(o);
                        return true;
                    case R.id.nav_account:
                        Toast.makeText(Home.this, "My Account", Toast.LENGTH_LONG).show();
                        Intent i=new Intent(Home.this,Profile.class);
                        startActivity(i);
                        return true;

                    case R.id.Cart:
                        Toast.makeText(Home.this, "Cart", Toast.LENGTH_LONG).show();
                        Intent cart=new Intent(Home.this,Cart.class);
                        startActivity(cart);

                        return true;
                    case R.id.nav_logout:
                        signOut();

                        return true;
                    case R.id.Payment:
                        Intent P=new Intent(Home.this,Payment.class);
                        startActivity(P);
                        Toast.makeText(Home.this, "logout", Toast.LENGTH_LONG).show();
                        return true;
                    case R.id.Address:
                        Intent A=new Intent(Home.this,Addresses.class);
                        startActivity(A);
                        Toast.makeText(Home.this, "Adresses", Toast.LENGTH_LONG).show();
                        return true;

                }return false;
            }
        });
        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        //Recycler code
        RecyclerView
                ParentRecyclerViewItem
                = findViewById(
                R.id.parent_recyclerview);

        // Initialise the Linear layout manager
        LinearLayoutManager
                layoutManager
                = new LinearLayoutManager(
                Home.this);

        // Pass the arguments
        // to the parentItemAdapter.
        // These arguments are passed
        // using a method ParentItemList()
        ParentItemAdapter
                parentItemAdapter
                = new ParentItemAdapter(
                ParentItemList());

        // Set the layout manager
        // and adapter for items
        // of the parent recyclerview
        ParentRecyclerViewItem
                .setAdapter(parentItemAdapter);
        ParentRecyclerViewItem
                .setLayoutManager(layoutManager);

    }

    // override the onOptionsItemSelected()
    // function to implement
    // the item click listener callback
    // to open and close the navigation
    // drawer when the icon is clicked

    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {

        switch(menuItem.getItemId()){
            case R.id.nav_account:
                Toast.makeText(Home.this, "item_1", Toast.LENGTH_LONG).show();
                return true;
            case R.id.Cart:
                Toast.makeText(Home.this, "in sd card", Toast.LENGTH_LONG).show();
                return true;
            default:
                return false;

        }
    }
    @Override
    public boolean onOptionsItemSelected(@NotNull  MenuItem item) {




            if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
                return true;
            }
            return super.onOptionsItemSelected(item);
        }










    private List<ParentItem> ParentItemList()
    {
        List<ParentItem> itemList
                = new ArrayList<>();

        ParentItem item
                = new ParentItem(
                "Featured Restaurants",
                ChildItemList());
        itemList.add(item);
        ParentItem item1
                = new ParentItem(
                "Featured Food",
                ResItemList());
        itemList.add(item1);
     

        return itemList;
    }

    // Method to pass the arguments
    // for the elements
    // of child RecyclerView
    private List<ChildItem> ChildItemList()
    {
        List<ChildItem> ChildItemList
                = new ArrayList<>();

        ChildItemList.add(new ChildItem("Macdonalds",productImages[0],0,0));
        ChildItemList.add(new ChildItem("Pizza Hot",productImages[1],1,0));
        ChildItemList.add(new ChildItem("Arabiata",productImages[2],2,0));
        ChildItemList.add(new ChildItem("Papa Johns",productImages[3],3,0));

        return ChildItemList;
    }    private List<ChildItem> ResItemList()
    {
        List<ChildItem> ChildItemList
                = new ArrayList<>();

        ChildItemList.add(new ChildItem("Shear Box",productImages[4],4,1));
        ChildItemList.add(new ChildItem("Cheese lovers",productImages[5],5,1));
        ChildItemList.add(new ChildItem("Oriental Dish",productImages[6],6,1));
        ChildItemList.add(new ChildItem("Margerita",productImages[7],7,1));

        return ChildItemList;
    }

    void signOut(){
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                finish();
                startActivity(new Intent(Home.this,SignUp.class));
            }
        });
    }
}