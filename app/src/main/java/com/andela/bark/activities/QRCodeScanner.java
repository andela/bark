package com.andela.bark.activities;

import android.app.Activity;
import android.os.Bundle;

import com.andela.bark.ticketVerification.TicketValidator;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by andela on 9/21/15.
 */
public class QRCodeScanner extends Activity implements ZXingScannerView.ResultHandler{
    private ZXingScannerView mScannerView;
    private TicketValidator ticketValidator;
    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
        ticketValidator = new TicketValidator(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result rawResult) {
        ticketValidator.validateTicketNumber(rawResult.getText());

    }
}
