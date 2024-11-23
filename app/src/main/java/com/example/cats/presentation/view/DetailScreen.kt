package com.example.cats.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.cats.R
import com.example.cats.data.remote.models.CatResponse
import com.example.cats.presentation.model.FeatureData
import com.example.cats.ui.theme.OrangeTextColor

@Composable
fun DetailScreen(cat: CatResponse?) {
    DetailScreenItem(cat = cat)
}


@Composable
fun DetailScreenItem(cat: CatResponse?) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = cat?.name.toString(),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            style = MaterialTheme.typography.headlineLarge,
            fontFamily = FontFamily.Serif,
            color = OrangeTextColor
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.Red)
        )
        Image(
            painter = rememberAsyncImagePainter(cat?.image?.url),
            contentDescription = cat?.name,
            modifier = Modifier
                .fillMaxHeight(0.4f)
                .padding(4.dp)
                .shadow(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(16.dp)
                )
                .clip(RoundedCornerShape(48.dp))
                .align(Alignment.CenterHorizontally),
            contentScale = ContentScale.FillHeight
        )
        Text(
            text = cat?.description.toString(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, top = 8.dp),
            fontSize = 14.sp,
            style = MaterialTheme.typography.bodyMedium
        )
        FeatureLineRow(cat)
    }
}

@Composable
fun FeatureLineRow(
    cat: CatResponse?
) {
    val scrollState = rememberScrollState()
    val featureList = listOf(
        FeatureData(
            text = "Child Friendly: ${cat?.child_friendly.toString()}",
            icon = R.drawable.ic_child,
            iconDescription = "Child Icon",
            iconTint = Color.Blue,
            widthFraction = 0.5f
        ),
        FeatureData(
            text = "Country Code: ${cat?.country_code.toString()}",
            icon = R.drawable.ic_flag,
            iconDescription = "Country Icon",
            iconTint = Color.Red,
            widthFraction = 1f
        ),
        FeatureData(
            text = "Dog Friendly: ${cat?.dog_friendly.toString()}",
            icon = R.drawable.ic_group,
            iconDescription = "Dog Friendly Icon",
            iconTint = Color.Blue,
            widthFraction = 0.5f
        ),
        FeatureData(
            text = "Intelligence: ${cat?.intelligence.toString()}",
            icon = R.drawable.ic_flag,
            iconDescription = "intelligence Icon",
            iconTint = Color.Red,
            widthFraction = 1f
        ), FeatureData(
            text = "Life Span: ${cat?.life_span.toString()}",
            icon = R.drawable.ic_child,
            iconDescription = "Life Span Icon",
            iconTint = Color.Blue,
            widthFraction = 0.5f
        ),
        FeatureData(
            text = "Health Issues: ${cat?.health_issues.toString()}",
            icon = R.drawable.ic_flag,
            iconDescription = "Health Issues Icon",
            iconTint = Color.Red,
            widthFraction = 1f
        ), FeatureData(
            text = "Energy Level: ${cat?.energy_level.toString()}",
            icon = R.drawable.ic_child,
            iconDescription = "Energy Level Icon",
            iconTint = Color.Blue,
            widthFraction = 0.5f
        ),
        FeatureData(
            text = "Social Needs: ${cat?.country_code.toString()}",
            icon = R.drawable.ic_flag,
            iconDescription = "Social Needs Icon",
            iconTint = Color.Red,
            widthFraction = 1f
        )
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .verticalScroll(scrollState)
    ) {
        featureList.chunked(2).forEach { rowFeatures ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                rowFeatures.forEach { feature ->
                    FeatureLine(
                        feature
                    )
                }
            }
        }

        Text(text = "For Detailed information click: ${cat?.wikipedia_url}",
            modifier = Modifier.clickable {
                //openUrl(context = , url = cat?.wikipedia_url.toString())
            })
    }
}

@Composable
fun FeatureLine(features: FeatureData) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(features.widthFraction),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(features.icon),
                contentDescription = features.iconDescription,
                modifier = Modifier
                    .size(24.dp),
                tint = features.iconTint
            )
            Text(
                text = features.text,
                modifier = Modifier.padding(12.dp),
                fontSize = 14.sp,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}


/*CatResponse(adaptability=5, affection_level=5, alt_names=, bidability=null, cat_friendly=null,
cfa_url=http://cfa.org/Breeds/BreedsAB/Abyssinian.aspx,
 child_friendly=3, country_code=EG, country_codes=EG,
 description=The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals.,
  dog_friendly=4, energy_level=5, experimental=0, grooming=1, hairless=0, health_issues=2, hypoallergenic=0, id=abys,
  image=Image(height=1445, id=0XYvRd7oD, url=https://cdn2.thecatapi.com/images/0XYvRd7oD.jpg, width=1204), indoor=0,
  intelligence=5, lap=1, life_span=14 - 15, name=Abyssinian, natural=1, origin=Egypt, rare=0, reference_image_id=0XYvRd7oD,
   rex=0, shedding_level=2, short_legs=0, social_needs=5, stranger_friendly=5, suppressed_tail=0,
   temperament=Active, Energetic, Independent, Intelligent, Gentle, vcahospitals_url=https://vcahospitals.com/know-your-pet/cat-breeds/abyssinian,
   vetstreet_url=http://www.vetstreet.com/cats/abyssinian, vocalisation=1, weight=Weight(imperial=7  -  10, metric=3 - 5),
    wikipedia_url=https://en.wikipedia.org/wiki/Abyssinian_(cat))
*/

