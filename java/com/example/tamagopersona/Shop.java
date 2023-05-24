package com.example.tamagopersona;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Shop extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        List<AllItem> allItemsList = new ArrayList<>();
        allItemsList.add(new AllItem("Miam", 5, "nigiri", 1000) );
        allItemsList.add(new AllItem("'Mon but dans la vie, c'est de respirer.'",  0.0, "altf4",1));
        allItemsList.add(new AllItem("Sois fainéant Tu vivras content", 5.0, "hohoho", 1));
        allItemsList.add(new AllItem("Le travail c'est la santé, ne rien faire c'est la conserver", 10.0, "go", 1));
        allItemsList.add(new AllItem("Pro de la grasse mat'", 15.0, "oyasumi", 1));
        allItemsList.add(new AllItem("Work smart not hard", 20.0, "todolist", 1));
        allItemsList.add(new AllItem("'Je le ferais demain.'", 25.0, "fire", 1));
        allItemsList.add(new AllItem("'Si j'aurais sû, je l'aurais fait...'", 30.0, "ohayo", 1));
        allItemsList.add(new AllItem("Finalement je vais m'y mettre !", 35.0, "workone", 1));
        allItemsList.add(new AllItem("Il n'a que dans le dictionnaire que réussite vient avant travail", 40.0, "worktwo", 1));
        allItemsList.add(new AllItem("Le monde appartient à ceux qui se lèvent tôt", 45.0, "flex",1));
        allItemsList.add(new AllItem("'J'ai terminé ce que j'avais à faire, ça te dit un mario kart ?'", 50.0, "flag", 1));
        allItemsList.add(new AllItem("Un vrai bosseur", 55.0, "sharktaf",1));
        allItemsList.add(new AllItem("'Arrête de travailler !'", 100.0, "stobit", 1));

        ListView shopListView = findViewById(R.id.shop_list_view);
        shopListView.setAdapter(new ItemAdapter(this, allItemsList));
    }

    public void goBackToMain(View view) {
        finish(); // Ferme l'activité "Shop" actuelle
    }

    public void showConfirmationDialog(final AllItem item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmation d'achat");
        builder.setMessage("Voulez-vous acheter cet article ?");
        builder.setPositiveButton("Oui", (dialog, which) -> {
            // Effectuer l'achat de l'article et mettre à jour le porte-monnaie
            double price = item.getPrice();
            // Autres actions à effectuer après l'achat
        });
        builder.setNegativeButton("Non", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}