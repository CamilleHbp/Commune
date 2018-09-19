object FakeData {
    val contactList = arrayOf<Contact>(
            Contact("Arthur", "Zoro", "0102030405"),
            Contact("Bertrand", "Yolo", "0203040506"),
            Contact("Camille", "Xanax", "0304050607"),
            Contact("Danielle", "Wallah", "0405060708"),
            Contact("Arthur", "Zoro", "0102030405"),
            Contact("Bertrand", "Yolo", "0203040506"),
            Contact("Camille", "Xanax", "0304050607"),
            Contact("Danielle", "Wallah", "0405060708"),
            Contact("Arthur", "Zoro", "0102030405"),
            Contact("Bertrand", "Yolo", "0203040506"),
            Contact("Camille", "Xanax", "0304050607"),
            Contact("Danielle", "Wallah", "0405060708"),
            Contact("Emelyne", "Voanjobory", "0506070809")
    )
}

data class Contact(val firstName: String, val lastName: String, val phoneNumber: String)