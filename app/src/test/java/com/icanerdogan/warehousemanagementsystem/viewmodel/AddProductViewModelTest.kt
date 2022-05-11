package com.icanerdogan.warehousemanagementsystem.viewmodel

import android.app.Application
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.platform.app.InstrumentationRegistry
import com.icanerdogan.warehousemanagementsystem.model.Product
import com.icanerdogan.warehousemanagementsystem.viewmodel.product.AddProductViewModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AddProductViewModelTest {

    @MockK
    private lateinit var addProductViewModel: AddProductViewModel

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp(){
        MockKAnnotations.init(this)
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

        addProductViewModel = AddProductViewModel(context as Application)
    }
    @Test
    fun controlCreateProductName(){
        val mockkProduct = mockk<Product>()
        every { mockkProduct.productID } returns 1
        every { mockkProduct.productName } returns "name"
        every { mockkProduct.productStock } returns 1
        every { mockkProduct.productModel } returns "model"
        every { mockkProduct.productBarcodeNumber } returns 1
        every { mockkProduct.productCategory } returns "Hammadde"

        val result : Product = addProductViewModel.createProduct(
            mockkProduct.productName!!,
            mockkProduct.productBarcodeNumber!!,
            mockkProduct.productModel!!,
            mockkProduct.productCategory!!
        )

        assertEquals(result.productName, "name")
    }

    @Test
    fun controlCreateProductIsNullProductID(){
        val mockkProduct = mockk<Product>()
        every { mockkProduct.productID } returns 1
        every { mockkProduct.productName } returns "name"
        every { mockkProduct.productStock } returns 1
        every { mockkProduct.productModel } returns "model"
        every { mockkProduct.productBarcodeNumber } returns 1
        every { mockkProduct.productCategory } returns "Hammadde"

        val result : Product = addProductViewModel.createProduct(
            mockkProduct.productName!!,
            mockkProduct.productBarcodeNumber!!,
            mockkProduct.productModel!!,
            mockkProduct.productCategory!!
        )

        assertEquals(result.productName, "name")
    }
}