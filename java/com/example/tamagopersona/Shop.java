package com.example.tamagopersona;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Shop extends AppCompatActivity {
    private Button backButton;

    //liste des items
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        List<AllItem> allItemsList = new ArrayList<>();
        allItemsList.add(new AllItem("Miam", 5, "nigiri", 1000, "Augmente la jauge de faim de 1") );
        allItemsList.add(new AllItem("Xanax", 15, "xanax", 100, "Augmente la jauge de bonheur de 1. Il est reconseillé d'aller doucement sur la dose...") );
        allItemsList.add(new AllItem("Truck Sama à la rescousse", 20, "trucksama", 15, "Votre personnage est décédé ? Permet de réinitialiser sa jauge de 0 à 10. Attention aux effets secondaires, ce n'est pas en mourrant qu'on vivra mieux.") );
        allItemsList.add(new AllItem("'Mon but dans la vie, c'est de respirer.'",  0.0, "altf4",1, "un succès à débloquer"));
        allItemsList.add(new AllItem("Sois fainéant Tu vivras content", 5.0, "hohoho", 1, "un succès à débloquer"));
        allItemsList.add(new AllItem("Le travail c'est la santé, ne rien faire c'est la conserver", 10.0, "go", 1, "un succès à débloquer"));
        allItemsList.add(new AllItem("Pro de la grasse mat'", 15.0, "oyasumi", 1, "un succès à débloquer"));
        allItemsList.add(new AllItem("Work smart not hard", 20.0, "todolist", 1, "un succès à débloquer"));
        allItemsList.add(new AllItem("'Je le ferais demain.'", 25.0, "fire", 1, "un succès à débloquer"));
        allItemsList.add(new AllItem("'Si j'aurais sû, je l'aurais fait...'", 30.0, "ohayo", 1, "un succès à débloquer"));
        allItemsList.add(new AllItem("Finalement je vais m'y mettre !", 35.0, "workone", 1, "un succès à débloquer"));
        allItemsList.add(new AllItem("Il n'a que dans le dictionnaire que réussite vient avant travail", 40.0, "worktwo", 1, "un succès à débloquer"));
        allItemsList.add(new AllItem("Le monde appartient à ceux qui se lèvent tôt", 45.0, "flex",1, "un succès à débloquer"));
        allItemsList.add(new AllItem("'J'ai terminé ce que j'avais à faire, ça te dit un mario kart ?'", 50.0, "flag", 1, "un succès à débloquer"));
        allItemsList.add(new AllItem("Un vrai bosseur", 55.0, "sharktaf",1, "un succès à débloquer"));
        allItemsList.add(new AllItem("'Arrête de travailler !'", 100.0, "stobit", 1, "un succès à débloquer"));

        ListView shopListView = findViewById(R.id.shop_list_view);
        ItemAdapter itemAdapter = new ItemAdapter(this, allItemsList);
        shopListView.setAdapter(itemAdapter);
        shopListView.setOnItemClickListener((parent, view, position, id) -> {
            AllItem selectedItem = allItemsList.get(position);
            showConfirmationDialog(selectedItem);
        });

        //bouton retour en arrière
        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBackToMain();
            }
        });
    }

    //fonction retour en arriere
    public void goBackToMain() {
        finish();
    }

    //si item cliqué, pop up qui apparaît pour avoir la description et pour pouvoir acheté un item
    public void showConfirmationDialog(final AllItem item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_item, null);
        builder.setView(dialogView);

        TextView titleTextView = dialogView.findViewById(R.id.dialog_title);
        TextView descriptionTextView = dialogView.findViewById(R.id.dialog_description);
        TextView priceTextView = dialogView.findViewById(R.id.dialog_price);

        titleTextView.setText(item.getName());
        descriptionTextView.setText(item.getDescription());
        priceTextView.setText("Prix : " + item.getPrice());

        builder.setPositiveButton("Acheter", (dialog, which) -> {
            if (item.getQuantity() > 0) {
                item.setQuantity(item.getQuantity() - 1);
                // Ajouter l'article à l'inventaire de MainActivity
                Intent resultIntent = new Intent();
                resultIntent.putExtra("ITEM_NAME", item.getName());
                resultIntent.putExtra("ITEM_IMGNAME", item.getImgName());
                setResult(RESULT_OK, resultIntent);
                Toast.makeText(Shop.this, "Quantité restante : " + item.getQuantity(), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(Shop.this, "Cet article est en rupture de stock.", Toast.LENGTH_SHORT).show();
            }
        });

        //bouton retour
        builder.setNegativeButton("Annuler", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
