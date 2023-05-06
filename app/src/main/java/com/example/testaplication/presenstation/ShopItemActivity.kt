package com.example.testaplication.presenstation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.testaplication.R
import com.example.testaplication.domain.ShopList
import com.google.android.material.textfield.TextInputLayout

class ShopItemActivity: AppCompatActivity() {

    private lateinit var viewModel: ShopItemViewModel

    private lateinit var tilName: TextInputLayout
    private lateinit var tilCount: TextInputLayout
    private lateinit var etName: EditText
    private lateinit var etCount: EditText
    private lateinit var button: Button

    private var screenMode = SCREEN_MODE_UNKNOWN
    private var shopItemId = ShopList.UNDEFINED_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_item)
        parseIntent()
        viewModel = ViewModelProvider(this)[ShopItemViewModel::class.java]
        initViews()
        editTextListeners()
        launchMode()
        observeViewModel()
    }

    companion object {
        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val EXTRA_SHOP_ITEM_ID = "extra_shop_item_id"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"
        private var SCREEN_MODE_UNKNOWN = ""


        fun newIntentItemAdd (context: Context) : Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return intent
        }

        fun editIntentItem(context: Context, shopItemId: Int): Intent {
            val intent = Intent(context,ShopItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
            intent.putExtra(EXTRA_SHOP_ITEM_ID, shopItemId)
            return intent
        }

    }

    private fun initViews() {
        tilName = findViewById(R.id.til_name)
        tilCount = findViewById(R.id.til_count)
        etName = findViewById(R.id.et_name)
        etCount = findViewById(R.id.et_count)
        button = findViewById(R.id.save_button)
    }

    private fun parseIntent() {
        if (!intent.hasExtra(EXTRA_SCREEN_MODE)) {
            throw RuntimeException("Param screen mode is absent")
        }

        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
        if (mode != MODE_ADD && mode != MODE_EDIT) {
            throw RuntimeException("Unknown screen mode $mode")
        }

        screenMode = mode
        if (screenMode == MODE_EDIT) {
            if (!intent.hasExtra(EXTRA_SHOP_ITEM_ID)) {
                throw RuntimeException("Param shop item id unknown")
            }
            shopItemId = intent.getIntExtra(EXTRA_SHOP_ITEM_ID, ShopList.UNDEFINED_ID)
        }
    }

    private fun editItem(){
        viewModel.getShopList(shopItemId)
        viewModel.itemShopList.observe(this) {
            etName.setText(it.name)
            etCount.setText(it.quantity.toString())

        }
        button.setOnClickListener {
            viewModel.editShopItem(etName.text?.toString(),etCount.text?.toString())
        }
    }

    private fun addItem() {
        button.setOnClickListener {
            viewModel.addNewShopItem(etName.text?.toString(),etCount.text?.toString())
        }
    }

    private fun editTextListeners() {
        etName.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resetInputName()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        etCount.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resetInputCount()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private fun launchMode(){
        when (screenMode) {
            MODE_EDIT -> editItem()
            MODE_ADD -> addItem()
            else -> throw RuntimeException("Unknown this screen mode: $screenMode")
        }
    }

    private fun observeViewModel() {
        viewModel.errorInputCount.observe(this){
            val message =  if (it) {
                getString(R.string.error_input_count)
            } else {
                null
            }
            tilCount.error = message
        }
        viewModel.errorInputName.observe(this){
            val message =  if (it) {
                getString(R.string.error_input_name)
            } else {
                null
            }
            tilName.error = message
        }

        viewModel.closeScreen.observe(this){
            finish()
        }
    }
}

