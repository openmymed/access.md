<template id="top-nav">   
    <nav this="el" class="navbar navbar-expand-lg navbar-light bg-transparent">
        <div class="d-flex col-12 flex-row-reverse justify-content-center justify-content-md-start p-2 align-items-center">
            <h6 id="profile-name" class="align-middle ml-2">
                {{name}}
            </h6>
            <img
                id="profile-pic"
                class="profile-img"
                src="https://upload.wikimedia.org/wikipedia/commons/thumb/7/7e/Circle-icons-profile.svg/768px-Circle-icons-profile.svg.png"
                alt="profile-pic"
                />
        </div>
    </nav>;
</template>
<script>
    Vue.component("top-nav", {
        template: "#top-nav",
        data: () => ({
                name: null,
            }),
            created(){
                this.name = sessionStorage.getItem("name");
            }
    });
</script>
