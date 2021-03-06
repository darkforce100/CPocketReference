package com.eventhorizonwebdesign.cpocketreference;
// The package is a method of encapsulation. Every class in a package has access to the others' protected methods and variables.
//KSP horse-did-nothing-good-word
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.eventhorizonwebdesign.cpocketreference.adapters.ExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
// Import statements are the equivalent to the C++ #include <>. These import the code from other classes or packages to be used in this one.

public class MainMenuActivity extends AppCompatActivity {
    // The class is another method of encapsulation. Typically, every file has a class inside it named after it.

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    // This is a special annotation. You don't need to know much about these except that onCreate methods must always override that of their superclass.
    protected void onCreate(Bundle savedInstanceState) {
        // The onCreate method defined here will override the contents of the onCreate method in the AppCompatActivity constructor that we are extending.
        super.onCreate(savedInstanceState);
        // This line calls the onCreate method of the superclass on any saved instance (reloads where the user left off)
        setContentView(R.layout.activity_main_menu);
        // You will use setContentView a lot. This inflates the activity_main_menu XML file and links it to this class.


        expListView = (ExpandableListView) findViewById(R.id.chapter_list);
        // To use an UI element, you have to declare and link its counterpart in Java.
        // This line does the following:
            // Gets the ExpandableListView object (Java) from above
            // Calls findViewById on the integer value R.id.chapter_list (This is a value generated by ADK. DO NOT EDIT R.java or R.class!!!!!!!!)
            // Typecasts the View object response to the subclass of view, ExpandableListView
            // Sets expListView to the result
            // chapter_list can now be programatically manipulated by using its Java counterpart, expListView

        prepareListData();
        // This line calls the method prepareListData. Notice that no prototype is required in Java.

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        // Every ListView needs a ListAdapter. This line makes a new ExpandableListAdapter object with the params:
            // 1. this - "this" is a keyword in java, meaning the next encapsulation method (usually class) up in the heirarchy of encapsulation
            // 2. listDataHeader - This only works because prepareListData has made the value non-null. This adds the header views.
            // 3. listDataChild - Same as above, this adds the children views.

        expListView.setAdapter(listAdapter);
        // This line just sets the list adapter

        // Context includes saved states and files linked in the Android manifest. This line creates a context that can be passed to other functions.
        final Context c = this.getApplicationContext();

        // onChildClick listener set on main ListView, pointing to an anonymous inner function
        expListView.expandGroup(0);
        expListView.expandGroup(1);
        expListView.expandGroup(2);
        expListView.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
            // Declare an intent to change Activities
            Intent intent;
            // Switch on parent of child that was clicked on
            switch(listDataHeader.get(groupPosition)){
                case "Reference":
                    //Switch on child
                    switch(listDataChild.get(
                            listDataHeader.get(groupPosition)).get(
                            childPosition)){
                        case "ASCII Table":
                            // Fire intent to new Activity
                            intent = new Intent(c, ASCIIListActivity.class);
                            startActivity(intent);
                            break;
                        case "Primitive Types":
                            intent = new Intent(c, PrimitiveListActivity.class);
                            startActivity(intent);
                            break;
                        case "Escape Characters":
                            intent = new Intent(c, EscapeListActivity.class);
                            startActivity(intent);
                            break;
                        case "Keywords":
                            intent = new Intent(c, KeywordListActivity.class);
                            startActivity(intent);
                            break;
                        case "Operators":
                            intent = new Intent(c, OperatorListActivity.class);
                            startActivity(intent);
                            break;
                        default:
                            Toast.makeText(getApplicationContext(), "An unexpected error occurred.", Toast.LENGTH_SHORT).show();
                            break;
                    }
                    break;
                case "Libraries":
                    intent = new Intent(c, ReferenceListActivity.class);
                    intent.putExtra("t", listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition));
                    startActivity(intent);
                    break;
                case "Tutorials":
                    intent = new Intent(c, TutorialActivity.class);
                    intent.putExtra("t", listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition));
                    startActivity(intent);
                    break;
                default:
                    Toast.makeText(getApplicationContext(), "An unexpected error occurred.", Toast.LENGTH_SHORT).show();
                    break;
            }
            return false;
        });
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<>();
        // This is the array that will hold the Strings for all the header titles.
        listDataChild = new HashMap<>();
        // This is the HashMap that will link header strings to their respective list of children

        // Adding header data
        listDataHeader.add("Reference");
        listDataHeader.add("Libraries");
        listDataHeader.add("Tutorials");

        // Adding child data
        List<String> reference = new ArrayList<>();
        reference.add("ASCII Table");
        reference.add("Primitive Types");
        reference.add("Escape Characters");
        reference.add("Keywords");
        reference.add("Operators");

        List<String> libraries = new ArrayList<>();
        libraries.add("iostream");
        libraries.add("fstream");
        libraries.add("iomanip");
        libraries.add("cmath");
        libraries.add("ctime");
        libraries.add("cctype");
        libraries.add("clocale");
        libraries.add("cfenv");
        libraries.add("csignal");
        libraries.add("cstdio");
        libraries.add("cstdlib");
        libraries.add("cwctype");
        libraries.add("cwchar");

        List<String> tutorials = new ArrayList<>();
        tutorials.add("Hello World");
        tutorials.add("Writing Statements");
        tutorials.add("Writing Expressions");
        tutorials.add("Conditionals");
        tutorials.add("For Loop");
        tutorials.add("While Loop");
        tutorials.add("Do-While Loop");
        tutorials.add("Functions");
        // TODO tutorials.add("Encapsulation");

        // Add links to HashMap
        listDataChild.put(listDataHeader.get(0), reference); // Header, Child data
        listDataChild.put(listDataHeader.get(1), libraries);
        listDataChild.put(listDataHeader.get(2), tutorials);
    }

}
