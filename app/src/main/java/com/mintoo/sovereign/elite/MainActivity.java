package com.mintoo.sovereign.elite;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView txtGold, txtBtc, txtConsole;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private final Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtGold = findViewById(R.id.txtGold);
        txtBtc = findViewById(R.id.txtBtc);
        txtConsole = findViewById(R.id.txtConsole);

        // Interactive Gateway Button Listeners with Dialog Popups
        findViewById(R.id.btnDeposit).setOnClickListener(v -> showModal("Deposit Gateway", "Secure 256-Bit Capital Deposit Vault active across Binance & Exness corridors."));
        findViewById(R.id.btnExecute).setOnClickListener(v -> showModal("Order Execution", "Multi-exchange order routed successfully with zero latency slippage."));
        findViewById(R.id.btnWithdraw).setOnClickListener(v -> showModal("Withdrawal Vault", "Instant liquidity payout requested. Funds secured under Sovereign Protection."));

        // Exchange Dock Navigation Listeners
        findViewById(R.id.btnUnified).setOnClickListener(v -> logAction("Switched to Unified Multi-Exchange Matrix"));
        findViewById(R.id.btnBinance).setOnClickListener(v -> logAction("Connected to Binance Futures Orderbook"));
        findViewById(R.id.btnBitget).setOnClickListener(v -> logAction("Connected to Bitget Copy-Trading Stream"));
        findViewById(R.id.btnExness).setOnClickListener(v -> logAction("Connected to Exness Raw Spread Gold Terminal"));
        findViewById(R.id.btnTV).setOnClickListener(v -> logAction("Loaded TradingView 120 FPS Chart Engine"));
        findViewById(R.id.btnQuotex).setOnClickListener(v -> logAction("Connected to Quotex Binary Expiry Gateway"));

        // Start High-Frequency Ticker Simulation Loop
        startTickerSimulation();
    }

    private void showModal(String title, String message) {
        new AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("CONFIRM", (dialog, which) -> {
                Toast.makeText(this, "Action Executed Successfully", Toast.LENGTH_SHORT).show();
                logAction(title + " confirmed and settled.");
            })
            .setNegativeButton("CANCEL", null)
            .show();
    }

    private void logAction(String msg) {
        String currentText = txtConsole.getText().toString();
        txtConsole.setText(currentText + "\n[LOG] " + msg);
    }

    private void startTickerSimulation() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                double goldPrice = 2675.03 + (random.nextDouble() * 1.2 - 0.6);
                double btcPrice = 94112.43 + (random.nextDouble() * 20.0 - 10.0);

                txtGold.setText(String.format("%.2f", goldPrice));
                txtBtc.setText(String.format("%.2f", btcPrice));

                handler.postDelayed(this, 100);
            }
        }, 100);
    }
}
